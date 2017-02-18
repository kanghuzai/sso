package org.taniwan.study.sso.common.mvc.bean;

public class PageRes {

	// 总页数
	private Integer allPageSize = 1;

	// 总条数
	private Integer allRecordSize = 0;

	// 每页条数
	private Integer pageRecodeSize = 1;

	// 剩余记录数，公式：总记录数-开始位置-返回个数
	private Integer restCount = 0;

	public Integer getRestCount() {
		return restCount;
	}

	/**
	 * 
	 * @param allRecordSize
	 *            所有记录数
	 * @param pageRecodeSize
	 *            每页条数
	 */
	public PageRes(Integer allRecordSize, Integer pageRecodeSize) {
		this.allRecordSize = allRecordSize;
		this.pageRecodeSize = pageRecodeSize;
	};

	public PageRes() {
	};

	public void setRestCount(Integer startNo, Integer resultSize) {

		this.restCount = allRecordSize - startNo - resultSize;
	}

	public Integer getAllPageSize() {
		if (this.allRecordSize == null || this.allPageSize <= 0) {
			throw new RuntimeException("allRecordSize is not null or must not lgt 0...");
		}
		if (this.pageRecodeSize == null || this.pageRecodeSize < 0) {
			throw new RuntimeException("pageRecodeSize is not null or must not lt 0...");
		}
		double allPageSizeTemp = (double)this.allRecordSize / (double)this.pageRecodeSize;
		this.allPageSize = (int) Math.ceil(allPageSizeTemp) > 0 ? (int) Math.ceil(allPageSizeTemp) : 1;
		return allPageSize;
	}

	public Integer getAllRecordSize() {
		return allRecordSize;
	}

	public void setAllRecordSize(Integer allRecordSize) {
		if (allRecordSize == null || allRecordSize.equals(0)) {
			this.allRecordSize = 0;
		} else {
			this.allRecordSize = allRecordSize;
		}
	}

	public Integer getPageRecodeSize() {
		return pageRecodeSize;
	}

	public void setPageRecodeSize(Integer pageRecodeSize) {
		if (pageRecodeSize == null) {
			this.pageRecodeSize = 1;
		} else if (pageRecodeSize < 0) {
			this.pageRecodeSize = 1;
		} else {
			this.pageRecodeSize = pageRecodeSize;
		}
	}

	@Override
	public String toString() {
		return "PageRes [allPageSize=" + allPageSize + ", allRecordSize=" + allRecordSize + ", pageRecodeSize="
				+ pageRecodeSize + ", restCount=" + restCount + "]";
	}

}
