package uz.pdp.model;

import com.google.gson.annotations.SerializedName;

public class SynItem{

	@SerializedName("pos")
	private String pos;

	@SerializedName("text")
	private String text;

	@SerializedName("fr")
	private Integer fr;

	@SerializedName("asp")
	private String asp;

	public void setPos(String pos){
		this.pos = pos;
	}

	public String getPos(){
		return pos;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setFr(Integer fr){
		this.fr = fr;
	}

	public Integer getFr(){
		return fr;
	}

	public void setAsp(String asp){
		this.asp = asp;
	}

	public String getAsp(){
		return asp;
	}

	@Override
 	public String toString(){
		return 
			"SynItem{" + 
			"pos = '" + pos + '\'' + 
			",text = '" + text + '\'' + 
			",fr = '" + fr + '\'' + 
			",asp = '" + asp + '\'' + 
			"}";
		}
}