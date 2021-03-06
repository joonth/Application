package dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import util.DBConnectionPool;
import vo.Member;

public interface MemberDao {

	 Member login(Member mem)throws Exception;
	 List<Member> getList()throws Exception;
	 void addMember(Member mem)throws Exception;
	 Member getMemberInfo(String mno) throws Exception;
	 void updateMember(Member mem)throws Exception;
	 void deleteMember(String mno)throws Exception;
	 
}
