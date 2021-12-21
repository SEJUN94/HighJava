package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.StringTokenizer;

/**
 * 간단한 웹 서버 예제
 */
public class MyHttpServer {
	private final int port = 7777;
	private final String encoding = "UTF-8";
	
	// 서버 시작
	public void start() {
		
		System.out.println("서버가 시작되었습니다.");
		try(ServerSocket server = new ServerSocket(port)) {
			
			while(true) {
				try {
					Socket socket = server.accept();
					
					// 요청을 처리할 스레드 생성 및 실행
					HttpHandler handler = new HttpHandler(socket);
					new Thread(handler).start();
					
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Http 요청 처리를 위한 Runnable 객체
	 * @author PC-14
	 *
	 */
	// Runnable로 implements한 이유는 나중에 스레드로 동작시키기 위해서
	private class HttpHandler implements Runnable {
		
		private final Socket socket;
		
		
		public HttpHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			OutputStream out = null;
			BufferedReader br = null;
			
			try {
				out = new BufferedOutputStream(socket.getOutputStream());
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				// 요청 헤더정보 파싱하기
				StringBuilder request = new StringBuilder();
				while(true) {
					String str = br.readLine();
					
					// str.equals("")의 " "가 Empty Line임  이게 나오면 빠져나오게됨.
					// 그 전까지(Header)는 StringBuilder에 차곡차곡 쌓이게됨.
					if(str.equals("")) break; // empty line
					
					request.append(str + "\n");
				}
				
				System.out.println("요청헤더:\n" + request.toString());
				System.out.println("---------------------------------");
				
				String reqPath = "";
				
				// 요청 페이지 정보 가져오기
				StringTokenizer st = new StringTokenizer(request.toString());
				
				while(st.hasMoreElements()) {
					String token = st.nextToken();
					if(token.startsWith("/")) {
						reqPath = token;
					}
				}
				
				// URL 디코딩 처리(한글 깨짐 문제)
				reqPath = URLDecoder.decode(reqPath, "UTF-8");
				
				// 웹컨텐츠 폴더 경로 설정
				String filePath = "./WecContent" + reqPath;
				
				// 해당 파일이름을 이용하여 Content-type정보 추출하기
				String contentType = URLConnection.getFileNameMap().getContentTypeFor(filePath);
				
				//css파일인 경우 인식이 안되서 추가함.
				if(contentType == null && filePath.endsWith(".css")) {
					contentType = "text/css";
				}
				
				System.out.println("ContentType => " + contentType);
				
				File file = new File(filePath);
				if(!file.exists()) {
					makeErrorPage(out, 404, "Not Found");
					return;
				}
				
				byte[] body = makeResponseBody(filePath);
				
				byte[] header = makeResponseHeader(body.length, contentType);
				
				// 요청헤더가 HTTP /1.0이나 그 이후의 버전을 지원할 경우
				// MIME헤더를 전송한다.
				if(request.toString().indexOf("HTTP/") != -1) {
					out.write(header);
				}
				
				System.out.println("응답헤더:\n" + new String(header));
				System.out.println("----------------------------------");
				
				out.write(body);
				out.flush();
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}finally {
				try {
					socket.close(); // 소켓닫기
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		/**
		 * 응답헤더 생성하기
		 * @param contentLength 응답내용 크기
		 * @param contentType 마임타입
		 * @return 바이트배열
		 */
		private byte[] makeResponseHeader(int contentLength, String contentType) {
			String header = "HTTP/1.1 200 OK\r\n"  // Status Line
					+ "Server: MyHTTPServer 1.0\r\n" // Header부분
					+ "Content-length: " + contentLength + "\r\n" 
					+ "Content-type: " + contentType
					+ "; charset=" + encoding + "\r\n\r\n"; // \r\n\r\n 이후 body
			
			return header.getBytes();
		}
		
		/**
		 * 응답내용 생성하기
		 * @param filePath 응답하기 사용할 파일 경로
		 * @return 바이트배열 데이터
		 */
		private byte[] makeResponseBody(String filePath) {
				FileInputStream fis = null;
				
				byte[] data = null;
				
				try {
					File file = new File(filePath);
					data = new byte[(int) file.length()];
					
					fis = new FileInputStream(file);
					fis.read(data);
					
				}catch(IOException ex) {
					ex.printStackTrace();
				}finally {
					if(fis != null) {
						try {
						fis.close();
					}catch(IOException e) {
						e.printStackTrace();
				}
			}
		}				
		
		return data;
	    }
		
		/**
		 * 에러 페이지 생성
		 * @param out
		 * @param statusCode
		 * @param errMsg
		 */
		private void makeErrorPage(OutputStream out, int statusCode, String errMsg) {
			String statusLine = "HTTP/1.1" +  " "
								+ statusCode + " "
								+ errMsg;
			
			try {
				out.write(statusLine.getBytes());
				out.flush();
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new MyHttpServer().start();
	}
}