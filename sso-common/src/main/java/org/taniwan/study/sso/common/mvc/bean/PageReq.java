package org.taniwan.study.sso.common.mvc.bean;

public class PageReq {

	private Integer pageIndex = 1;
	private Integer pageSize = 15;

	/**
	 * @return the pageIndex
	 */
	public Integer getPageIndex() {
		return pageIndex;
	}

	/**
	 * @param pageIndex
	 *            the pageIndex to set
	 */
	public void setPageIndex(Integer pageIndex) {
		pageIndex = pageIndex <= 0 ? 0 : pageIndex;
		this.pageIndex = pageIndex;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		pageSize = pageSize <= 0 ? 0 : pageSize;
		this.pageSize = pageSize;
	}

	public Integer getStartNo() {
		return (pageIndex - 1) * pageSize;
	}

	public Integer getRowCount() {
		return pageSize;
	}

	@Override
	public String toString() {
		return "PageReq [pageIndex=" + pageIndex + ", pageSize=" + pageSize + "]";
	}

}
