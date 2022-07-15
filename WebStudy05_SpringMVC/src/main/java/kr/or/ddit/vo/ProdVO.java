package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 상품에 관한 정보를 가진 Domain Layer
 */
@Data
@EqualsAndHashCode(of= {"prodId"})
@ToString(exclude= {"prodDetail"})
public class ProdVO implements Serializable {
	
	private int rnum;
	
	@NotBlank(groups= {UpdateGroup.class, DeleteGroup.class})
	private String prodId;
	@NotBlank
	private String prodName;
	@NotBlank
	private String prodLgu;
	private String lprodNm;
	@NotBlank
	private String prodBuyer;
	private BuyerVO buyer;
	@Min(0)
	private Integer prodCost;
	private Integer prodPrice;
	private Integer prodSale;
	@NotBlank
	private String prodOutline;
	private String prodDetail;
	private String prodImg;
	
	private MultipartFile prodImage;
	
	private Integer prodTotalstock;
	private String prodInsdate;
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
	private Set<MemberVO> memberSet; // ProdVO has many MemberVO , 1:N
}













