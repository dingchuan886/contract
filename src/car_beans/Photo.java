package car_beans;

import java.util.Date;
import java.util.List;

public class Photo {

	private Long pid;

	private String url;

	private String smallUrl;

	private String detail;

	private int point;

	private long photoSize;

	//private Album album;

	private Date createDate;

	private Long userId;

	private List comments;

	private int deleted;

	private int width;

	private int height;

	private int uped;

	private int resize;

	private String ext;

	private int checked;

	private int space;

	private long photoOrder;

//	public Album getAlbum() {
//		return album;
//	}
//
//	public void setAlbum(Album album) {
//		this.album = album;
//	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getPid() {
		return pid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List getComments() {
		return comments;
	}

	public void setComments(List comments) {
		this.comments = comments;
	}

	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}

	public String getSmallUrl() {
		return smallUrl;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public long getPhotoSize() {
		return photoSize;
	}

	public void setPhotoSize(long photoSize) {
		this.photoSize = photoSize;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getUped() {
		return uped;
	}

	public void setUped(int uped) {
		this.uped = uped;
	}

	public int getResize() {
		return resize;
	}

	public void setResize(int resize) {
		this.resize = resize;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		this.space = space;
	}

	public long getPhotoOrder() {
		return photoOrder;
	}

	public void setPhotoOrder(long photoOrder) {
		this.photoOrder = photoOrder;
	}
}
