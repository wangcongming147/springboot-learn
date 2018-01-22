package com.learn.springboot.data;

import java.io.Serializable;

import lombok.Setter;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 
 * @ClassName:  PageableData   
 * @Description:分页 
 * @author: wangcongming
 * @date:   2017年12月31日 上午11:06:12   
 *
 */
@Setter
public class PageableData implements Serializable,Pageable{

	private static final long serialVersionUID = 6371998583927182081L;
	
	private int pageNumber;
	private int pageSize;
	private int offset;
	private Sort sort;

	@Override
	public int getPageNumber() {
		return this.pageNumber;
	}

	@Override
	public int getPageSize() {
		return this.pageSize;
	}

	@Override
	public int getOffset() {
		return this.offset;
	}

	@Override
	public Sort getSort() {
		return this.getSort();
	}

	@Override
	public Pageable next() {
		return null;
	}

	@Override
	public Pageable previousOrFirst() {
		return null;
	}

	@Override
	public Pageable first() {
		return null;
	}

	@Override
	public boolean hasPrevious() {
		return false;
	}
}
