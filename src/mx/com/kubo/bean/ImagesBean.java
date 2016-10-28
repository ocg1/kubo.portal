package mx.com.kubo.bean;

public class ImagesBean {
	
	private Integer width;
	private Integer height;
	private String urlImg;
	private String pathOriginal;
	private Integer typeLogo;
	private boolean isSave;
	private boolean thisSave;
	
	private String urlImgThumb;
	
	public ImagesBean(){
		
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getUrlImg() {
		return urlImg;
	}
	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
	public String getPathOriginal() {
		return pathOriginal;
	}
	public void setPathOriginal(String pathOriginal) {
		this.pathOriginal = pathOriginal;
	}
	public Integer getTypeLogo() {
		return typeLogo;
	}
	public void setTypeLogo(Integer typeLogo) {
		this.typeLogo = typeLogo;
	}
	public boolean isThisSave() {
		return thisSave;
	}
	public void setThisSave(boolean thisSave) {
		this.thisSave = thisSave;
	}
	public boolean isSave() {
		return isSave;
	}
	public void setSave(boolean isSave) {
		this.isSave = isSave;
	}
	public String getUrlImgThumb() {
		return urlImgThumb;
	}
	public void setUrlImgThumb(String urlImgThumb) {
		this.urlImgThumb = urlImgThumb;
	}
	

}
