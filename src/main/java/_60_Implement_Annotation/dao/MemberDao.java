package _60_Implement_Annotation.dao;

import _60_Implement_Annotation.vo.Member;

import java.util.List;

public interface MemberDao {
    /*
    * interface는 상속을 전제로 하므로 자동으로 public속성이 부여된다.
    */
    List<Member> selectList() throws Exception;
    int insert(Member member) throws Exception;
    int delete(int no) throws Exception;
    Member selectOne(int no) throws Exception;
    int update(Member member) throws Exception;
    Member exist(String email, String password) throws Exception;
}
