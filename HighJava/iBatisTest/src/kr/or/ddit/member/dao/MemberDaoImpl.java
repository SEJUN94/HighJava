package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDao {
	
	// 싱글턴 패턴 1번 자기 자신 class의 참조변수를 멤버변수로 선언한다.
	private static IMemberDao memDao;
	
	private SqlMapClient smc;
	
	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}
	
	// 1. 자료 입력
	@Override
	public int InsertMember(MemberVO mv) {

		
		int cnt = 0;
		
		try {
			cnt = smc.update("member.insertMember", mv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return cnt;
	}
	
	// 아이디 존재 여부 확인
	@Override
	public boolean checkMember(String memId) {

		boolean isExist = false;
		
		try {
			int cnt = (int) smc.queryForObject("member.ckeckMember", memId);
			
			if(cnt > 0) {
				isExist = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isExist;
	}
	
	// 3. 자료 수정
	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		
		try {
			cnt= smc.update("member.updateMember",mv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	// 2. 자료 삭제
	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		
		try {
			cnt = smc.delete("member.deleteMember", memId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	// 4.전체 자료 출력
	@Override
	public List<MemberVO> getAllMemberList() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			memList = smc.queryForList("member.getMemberAll");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return memList;
	}

	// 5. 자료 검색
	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<>();

	
			try {
				memList = smc.queryForList("member.searchMember", mv);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return memList;
	}
	
}
