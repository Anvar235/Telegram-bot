package uz.pdp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TrItem{

	@SerializedName("pos")
	private String pos;

	@SerializedName("mean")
	private List<MeanItem> mean;

	@SerializedName("text")
	private String text;

	@SerializedName("fr")
	private Integer fr;

	public void setPos(String pos){
		this.pos = pos;
	}

	public String getPos(){
		return pos;
	}

	public void setMean(List<MeanItem> mean){
		this.mean = mean;
	}

	public List<MeanItem> getMean(){
		return mean;
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

	@Override
 	public String toString(){
		return 
			"TrItem{" + 
			"pos = '" + pos + '\'' + 
			",mean = '" + mean + '\'' + 
			",text = '" + text + '\'' + 
			",fr = '" + fr + '\'' + 
			"}";
		}
}