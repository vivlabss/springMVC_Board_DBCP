package com.isoo.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.isoo.VO.MvcboardVO;

public class MvcBoardDAO {
	
	DataSource dataSource;																// 작업다 하고 나서 주석처리햐애함
	
	JdbcTemplate template = null;
	
	
	public MvcBoardDAO() {
		
		try {
			Context context = new InitialContext();										// 작업다 하고 나서 주석처리해애함
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");		// 작업다 하고 나서 주석처리해애함
			System.out.println("연결성공 : " + dataSource.getConnection());				// 작업다 하고 나서 주석처리해애함
		} catch(Exception e) {															// 작업다 하고 나서 주석처리해애함
			e.printStackTrace();														// 작업다 하고 나서 주석처리해애함	
			System.out.println("연결실패");												// 작업다 하고 나서 주석처리해애함	
		} 
		template = Constant.template;													// 작업다 하고 나서 주석처리해애함	
	}
	

	
	
	
/*	
	// 디비연결하고 테이블에 메인글을 저장하는 메소드
	public void insert(String name, String title, String content) {
		System.out.println("MvcBoardDAO insert()"); // 생략가능(로그)
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 데이터베이스와 연결한다.
			conn = dataSource.getConnection();
			
			/// 실행할 SQL 명령을 만든다.
			String sql = "insert into mvc_board(idx, name, title, content, ref, step, indent)" + "values(mvc_board_idx_seq.nextval, ?, ?, ?, mvc_board_idx_seq.currval, 0, 0)";

			// SQL 명령을 실행할 준비를 한다.
			pstmt = conn.prepareStatement(sql);
			
			// ? 부분 채워넣기
			pstmt.setString(1, name);  // 여기서 1은 첫번째 물음표라는 뜻(자바나 다른것들은 0부터 시작인데, 오라클은 1이 처음!!)
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			// SQL 명령을 실행
			// executeUpdate() : 테이블 내용이 변경되는 SQL 명령(insert,delete,update)을 실행한다.
			// executeQuery() : 테이블 내용이 변경되지않는 SQL 명령(select)을 실행한다.
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
*/	
	public void insert(final String name, final String title, final String content) {
		String sql = "insert into mvc_board(idx, name, title, content, ref, step, indent)" + "values(mvc_board_idx_seq.nextval, ?, ?, ?, mvc_board_idx_seq.currval, 0, 0)";
//				update() : insert, delete, update 명령을 실행한다.
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, name); 
				pstmt.setString(2, title);
				pstmt.setString(3, content);
			}
		});
	}
	
	
	
	
	
	
	
	
	
/*	
	// 테이블 전체
	//		 데이터 한건 한건을 저장하는 arraylist
	public ArrayList<MvcboardVO> list() {
		System.out.println("MvcBoardDAO list()"); // 로그(생략가능)
		
		// 테이블에서 글 전체를 읽어 저장한 후 리턴시킬 ArrayList를 선언만함.
		ArrayList<MvcboardVO> list = null;
		
		
		MvcboardVO vo = null;

		
		
		// 디비 연결에 필요한  선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// select 하러가니까
		ResultSet rs = null;

		
		try {
			// 디비랑 연결
			conn = dataSource.getConnection();
			
			// SQL 명령문(쿼리) 실행.
			// 글 그룹단위(ref) + 내림차순 정렬로 나오고,
			// 같은 ref내에서는 step의 오름차순으로 정렬하여 얻어오기
			String sql = "select * from mvc_board order by ref desc, step asc";
			pstmt = conn.prepareStatement(sql);
			
			// sql명령을 실행 ( select 명령을 실행하여 꺼내온 결과물을 저장해줘야함!! ResultSet 객체에다가 저장함)
			rs = pstmt.executeQuery();
			
			// next() : ResultSet 객체에 저장된 다음 데이터로 이동
			// 다음 데이터가 있으면 true, 없으면 false를 리턴한다.
			if(rs.next()) {
				list = new ArrayList<MvcboardVO>();
				do {
					vo = requestvo(rs);
					
					//  MvcboardVO.java 에서 멤버변수 9개 다 받는 생성자 만들고오기!

					// 익명으로
					list.add(vo);
				} while(rs.next());  // do 에다가 바로 rs.next 하게되면 위에 if에서 next하고 또 곧바로 next 되기 때문에 첫번째 게시글? 이 건너뛰어지게됨. 그래서 do~while을 써줌
			} else {
				System.out.println("테이블에 저장된 데이터가 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
*/
	public ArrayList<MvcboardVO> list() {
/*		ArrayList<MvcboardVO> list = null;
		String sql = "select * from mvc_board order by ref desc, step asc";
//												query() : 실행 결과가 여러개인 select 명령을 실행한다.		
		list = (ArrayList<MvcboardVO>) template.query(sql, new BeanPropertyRowMapper<MvcboardVO>(MvcboardVO.class));
//																BeanPropertyRowMapper() :  객체가 리턴하는 클래스에는 반드시 기본 생성자가 있어야한다 ==> 없으면 추상클래로 오인하여 500에러가 발생됨!!!!!!		
		return list;
*/
		String sql = "select * from mvc_board order by ref desc, step asc";
		return (ArrayList<MvcboardVO>) template.query(sql, new BeanPropertyRowMapper<MvcboardVO>(MvcboardVO.class));
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	// 중복되는 코드 뽑아내서 메소드로! 
	private MvcboardVO requestvo(ResultSet rs) throws SQLException {
		MvcboardVO vo;
		int idx = rs.getInt("idx");
		String name = rs.getString("name");
		String title= rs.getString("title");
		String content = rs.getString("content");
		Date writeDate = rs.getTimestamp("writeDate"); // 이걸 getString으로 가져와도 되지만 그렇게하면 나중에 sdf 안먹음(날짜서식적용불가). 여기서는 날짜랑 시간 둘 다 꺼내오려고 getTimeStamp함. 날짜만 꺼내오려면 getDate. 시간만 꺼내오려면 getTime
		int readCount = rs.getInt("readCount");
		int ref = rs.getInt("ref");
		int step = rs.getInt("step");
		int indent = rs.getInt("indent");
		vo = new MvcboardVO(idx, name, title, content, writeDate, readCount, ref, step, indent);
		return vo;
	} 
	
	
	
	
	
	
	
	
	
	
/*	
// 글 한건 꺼내오는 메소드 (넘어오는 글번호가 int형이므로(ViewService클래스에서 int형으로 형변환해줬으니!) int idx를 인수로)
	public MvcboardVO view(int no) {
		System.out.println("MvcBoardDAO view()"); //로그(생략가능)
		
		
// 해당 글을 얻어오기 전에 조회수를 증가시킨다. (넘겨받은 idx로 )
//		upHit(idx);
		
		
		// 테이블에서 글 1건을 읽어 저장한 후 리턴시킬 MvcBoardVO 객체를 선언한다. 
		MvcboardVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "select * from mvc_board where idx = ?";  // ? 자리에는, view()가 넘겨받은 int idx가 들어감!
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			// 1건 기억하는 
			rs = pstmt.executeQuery();
			
			// next() : ResultSet 객체에 저장된 다음 데이터로 이동
			// 다음 데이터가 있으면 true, 없으면 false를 리턴한다.
			if(rs.next()) {
				vo = requestvo(rs);
			} else {
				System.out.println("해당 글은 존재하지 않습니다.");  //url에 곧바로 idx 입력해서 들어오는 경우, 없는 idx를 클라이언트가 쳤을 경우를 대비!
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
*/
	public MvcboardVO view(int no) {
/*		MvcboardVO vo = null;
		String sql = "select * from mvc_board where idx = " + no;  // jdbc 할때는 no 자리에 ?가 들어갔었는데, 여기선 ..??
//						query() : 실행 결과가 여러개인 select 명령을 실행한다.	
		vo = template.queryForObject(sql, new BeanPropertyRowMapper<MvcboardVO>(MvcboardVO.class));
		return vo;
*/
		String sql = "select * from mvc_board where idx = " + no;
		return template.queryForObject(sql, new BeanPropertyRowMapper<MvcboardVO>(MvcboardVO.class));
	}

	
	
	
	
	
	
	
/*	
	// 조회수 1을 증가시킬 메소드
	public void upHit(int idx) {
		System.out.println("MvcBoardDAO upHit()"); //로그(생략가능)
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "update mvc_board set readCount = readCount + 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
*/
	public void upHit(final int idx) {
		String sql = "update mvc_board set readCount = readCount + 1 where idx = ?";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, idx); 
			}
		});
	}
	
	
	
		
		
	
	
	
	
/*	
	// 삭제
	public void delete(int idx) {
		System.out.println("MvcBoardDAO delete()"); //로그(생략가능)
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "delete from mvc_board where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
*/
	public void delete(final int idx) {	
		String sql = "delete from mvc_board where idx = ?";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, idx); 
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
/*	
	// 수정
	public void update(int idx, String title, String content) {
		System.out.println("MvcBoardDAO update()"); //로그(생략가능)
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "update mvc_board set title = ?, content = ?  where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, idx);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
*/	
	public void update(final int idx, final String title, final String content) {
		String sql = "update mvc_board set title = ?, content = ?  where idx = ?";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, title); 
				pstmt.setString(2, content); 
				pstmt.setInt(3, idx); 
			}
		});
	}
	
	
	
	

	
/*	
	// 답글
	public MvcboardVO reply(int idx) {
		System.out.println("MvcBoardDAO reply()"); //로그(생략가능)

		MvcboardVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "select * from mvc_board where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery(); // 글 한건 꺼내와서 담음

			
			if(rs.next()) {
				vo = requestvo(rs);
			} else {
				System.out.println("해당 글은 존재하지 않습니다.");  //url에 곧바로 idx 입력해서 들어오는 경우, 없는 idx를 클라이언트가 쳤을 경우를 대비!
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
*/
	public MvcboardVO reply(int idx) {
		String sql = "select * from mvc_board where idx = " + idx;
		return template.queryForObject(sql, new BeanPropertyRowMapper<MvcboardVO>(MvcboardVO.class));
	}
	
	
	
	
	
	
	
	
/*
	public void replyOK(int idx, String name, String title, String content, int ref, int step, int indent) {

		System.out.println("MvcBoardDAO replyOK()"); //로그(생략가능)
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
	
			String sql = "update mvc_board set step = step + 1 where ref = ? and step > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, step);
			pstmt.executeUpdate();
			
			// 답글이 달리면 최신 답글이 위에 오도록
			sql = "insert into mvc_board(idx, name, title, content, ref, step, indent)" + "values(mvc_board_idx_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setInt(4, ref);
			pstmt.setInt(5, step + 1);
			pstmt.setInt(6, indent + 1);
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
*/
	public void replyOK(int idx, final String name, final String title, final String content, final int ref, final int step, final int indent) {
		String sql = "update mvc_board set step = step + 1 where ref = ? and step > ?";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, ref); 
				pstmt.setInt(2, step); 
			}
		});
		sql = "insert into mvc_board(idx, name, title, content, ref, step, indent)" + "values(mvc_board_idx_seq.nextval, ?, ?, ?, ?, ?, ?)";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, name); 
				pstmt.setString(2, title); 
				pstmt.setString(3, content); 
				pstmt.setInt(4, ref); 
				pstmt.setInt(5, step + 1); 
				pstmt.setInt(6, indent + 1); 
			}
		});
	}

}