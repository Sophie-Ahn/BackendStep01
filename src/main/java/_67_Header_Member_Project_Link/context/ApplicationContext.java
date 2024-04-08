package _67_Header_Member_Project_Link.context;

import _67_Header_Member_Project_Link.annotation.Component;
import org.reflections.Reflections;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

// application-context.properties 파일을 읽어서 필요한 객체를 준비/과정
// @Component 어노테이션을 읽어서 자동으로 객체 준비/저장
// Container 클래스
public class ApplicationContext {
    // Container 맵 저장소
    // properties파일이 key=value구조로 되어 있기때문에 Map을 사용한다.
    Map<String, Object> objTable = new Hashtable<>();

    public Object getBean(String key){
        return objTable.get(key);
    }

    public ApplicationContext(String propertiesPath) throws Exception {
        Properties props = new Properties();
        props.load(new FileReader(propertiesPath));

        prepareObjects(props); // properties파일을 읽어서 객체 생성
        prepareAnnotationObjects(); // @Component 어노테이션이 선언된 클래스 객체 생성
        injectDependency(); // 객체간 의존성 주입

        // 어떤 객체가 준비되었는지 출력
        printAllPrepareObjects();
    }

    private void printAllPrepareObjects(){
        for(String key : objTable.keySet()){
            System.out.printf("%s = %s\n", key, objTable.get(key).getClass().getName());
        }
    }

    private void prepareAnnotationObjects() throws Exception {
        Reflections reflector = new Reflections("");

        Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
        String key = null;
        for(Class<?> clazz : list){
            key = clazz.getAnnotation(Component.class).value();
            objTable.put(key, clazz.getDeclaredConstructor().newInstance());
        }
    }

    // properties 파일을 읽어서 객체를 찾아서 참조하거나 생성한다.
    private void prepareObjects(Properties props) throws Exception{
        Context ctx = new InitialContext();
        String key = null;
        String value = null;

        for(Object item : props.keySet()){
            key = (String)item;
            value = props.getProperty(key);

            // Tomcat의 DataSource는 객체를 찾아서 참초값을 얻는다.
            if(key.startsWith("jndi.")){
                objTable.put(key, ctx.lookup(value));
            }
            // 우리가 직접 new로 객체를 생성한다.
            else {
                Class<?> classInfo = Class.forName(value); // 해당되는 클래스 정보
                // 클래스 정보를 바탕으로 객체를 동적 생성해서 objTable에 key와 value(객체)를 넣는다.
                 objTable.put(key, Class.forName(value).getDeclaredConstructor().newInstance());
            }
        }
    }

    // MemberDao에는 DataSource를 주입
    // Controller 상속받은 객체에는 MemberDao 객체를 주입
    private void injectDependency() throws Exception{
        for(String key : objTable.keySet()){

            // DataSource객체를 제외한 나머지는 모두 외부에서 객체를 주입해야 할 setter가 있다.
            if(!key.startsWith("jndi.")){
                callSetter(objTable.get(key));
            }
        }
    }

    private void callSetter(Object obj) throws Exception{
        Object dependency = null;

        // 객체를 통해 클래스 정보를 얻고, 클래스 정보로 모든 메서드 정보를 가져온다.
        Method[] ms = obj.getClass().getMethods();
        for(Method m : ms){
            // setter 메서드인가? 확인
            if(m.getName().startsWith("set")){

                // setter의 1번째 파라미터(즉, 주입해야 할 객체 타입) 정보
                dependency = findObjectByType(m.getParameterTypes() [0]);

                // 해당 파라미터에 해당하는 객체를 찾아서 반환받았으면,
                // setter를 호출하고, 1번째 파라미터에 객체를 넣는다.
                if(dependency != null) {
                    m.invoke(obj, dependency);
                }
            }
        }
    }

    private Object findObjectByType(Class<?> type){
        // objTable에 있는 모든 value값(즉, 생성된 객체들)
        for(Object obj : objTable.values()){
            // 객체가 type과 일치하느냐? 판단하기
            if(type.isInstance(obj)){
                return obj;
            }
        }

        return null;
    }
}
