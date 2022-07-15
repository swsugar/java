package kr.or.ddit.memo;

import java.io.IOException;
import java.util.Base64;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.DeleteGroup;
import kr.or.ddit.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="code")
@ToString(exclude= {"profileImage", "profileImg"})
public class MemoVO {
	@Min(value=1, groups= {UpdateGroup.class, DeleteGroup.class})
	private int code;
	@NotBlank
	private String writer;
	private String content;
	
	private MultipartFile profileImage; // 클라이언트가 업로드하는 이미지를 받기 위한 프로퍼티.
	private byte[] profileImg; // 데이터베이스의 BLOB 컬럼을 받기 위한 프로퍼티.
	
	public void setProfileImage(MultipartFile profileImage) throws IOException {
		if(profileImage!=null && !profileImage.isEmpty()) {
			if(!profileImage.getContentType().startsWith("image/")) {
				throw new IllegalArgumentException(String.format("%s 타입의 파일은 이미지가 아님.", profileImage.getContentType()));
			}
			this.profileImage = profileImage;
			this.profileImg = profileImage.getBytes();
		}
	}
	
	public String getBase64ProfileImage() {
//		commons-codec
		if(profileImg!=null) {
			return Base64.getEncoder().encodeToString(profileImg);
		}else {
			return null;
		}
	}
//	BLOB
}










