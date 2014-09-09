package com.netbrasoft.gnuob.generic.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.netbrasoft.gnuob.exception.GNUOpenBusinessServiceException;
import com.netbrasoft.gnuob.generic.GenericTypeWebService;
import com.netbrasoft.gnuob.generic.OrderBy;
import com.netbrasoft.gnuob.generic.Paging;
import com.netbrasoft.gnuob.generic.Parameter;
import com.netbrasoft.gnuob.generic.content.Content;
import com.netbrasoft.gnuob.generic.security.MetaData;
import com.netbrasoft.gnuob.generic.security.SecuredGenericTypeService;

@WebService(targetNamespace = "http://gnuob.netbrasoft.com/")
@Stateless(name = "CategoryWebServiceImpl")
public class CategoryWebServiceImpl<C extends Category> implements GenericTypeWebService<C> {

	@EJB(beanName = "SecuredGenericTypeServiceImpl")
	private SecuredGenericTypeService<C> securedGenericCategoryService;

	@EJB(beanName = "SecuredGenericTypeServiceImpl")
	private SecuredGenericTypeService<Content> securedGenericContentService;

	@Override
	@WebMethod(operationName = "countCategory")
	public long count(@WebParam(name = "metaData", header = true) MetaData metadata, @WebParam(name = "category") C type) throws GNUOpenBusinessServiceException {
		try {
			return securedGenericCategoryService.count(metadata, type);
		} catch (Exception e) {
			throw new GNUOpenBusinessServiceException(e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "findCategoryById")
	public C find(@WebParam(name = "metaData", header = true) MetaData metadata, @WebParam(name = "category") C type) throws GNUOpenBusinessServiceException {
		try {
			return securedGenericCategoryService.find(metadata, type, type.getId());
		} catch (Exception e) {
			throw new GNUOpenBusinessServiceException(e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "findCategory")
	public List<C> find(@WebParam(name = "metaData", header = true) MetaData metadata, @WebParam(name = "category") C type, @WebParam(name = "paging") Paging paging, @WebParam(name = "orderBy") OrderBy orderBy) throws GNUOpenBusinessServiceException {
		try {
			List<Example> examples = getSubCategoryExamples(type.getSubCategories());
			Parameter parameter = Parameter.getInstance("subCategories", Restrictions.or(examples.toArray(new Example[examples.size()])));
			return securedGenericCategoryService.find(metadata, type, paging, orderBy, parameter);
		} catch (Exception e) {
			throw new GNUOpenBusinessServiceException(e.getMessage(), e);
		}
	}

	private List<Example> getSubCategoryExamples(Set<SubCategory> subCategories) {
		List<Example> examples = new ArrayList<Example>();

		if (subCategories != null) {
			for (SubCategory subCategory : subCategories) {
				examples.add(Example.create(subCategory));
			}
		}

		return examples;
	}

	@Override
	@WebMethod(operationName = "mergeCategory")
	public C merge(@WebParam(name = "metaData", header = true) MetaData metadata, @WebParam(name = "category") C type) throws GNUOpenBusinessServiceException {
		try {
			persistMergeContents(metadata, type.getContents());
			persistMergeSubCategoryContents(metadata, type.getSubCategories());
			securedGenericCategoryService.merge(metadata, type);
			return type;
		} catch (Exception e) {
			throw new GNUOpenBusinessServiceException(e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "persistCategory")
	public C persist(@WebParam(name = "metaData", header = true) MetaData metadata, @WebParam(name = "category") C type) throws GNUOpenBusinessServiceException {
		try {
			persistMergeContents(metadata, type.getContents());
			persistMergeSubCategoryContents(metadata, type.getSubCategories());
			securedGenericCategoryService.persist(metadata, type);
			return type;
		} catch (Exception e) {
			throw new GNUOpenBusinessServiceException(e.getMessage(), e);
		}
	}

	private void persistMergeContents(MetaData metadata, Set<Content> contents) {
		if (contents != null && !contents.isEmpty()) {
			for (Content content : contents) {
				if (content.getId() == 0) {
					securedGenericContentService.persist(metadata, content);
				} else {
					securedGenericContentService.merge(metadata, content);
				}
			}
		}
	}

	private void persistMergeSubCategoryContents(MetaData metadata, Set<SubCategory> subCategories) {
		if (subCategories != null && !subCategories.isEmpty()) {
			for (SubCategory subCategory : subCategories) {
				persistMergeContents(metadata, subCategory.getContents());
				persistMergeSubCategoryContents(metadata, subCategory.getSubCategories());
			}
		}
	}

	@Override
	@WebMethod(operationName = "refreshCategory")
	public C refresh(@WebParam(name = "metaData", header = true) MetaData metadata, @WebParam(name = "category") C type) throws GNUOpenBusinessServiceException {
		try {
			return securedGenericCategoryService.refresh(metadata, type, type.getId());
		} catch (Exception e) {
			throw new GNUOpenBusinessServiceException(e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "removeCategory")
	public void remove(@WebParam(name = "metaData", header = true) MetaData metadata, @WebParam(name = "category") C type) throws GNUOpenBusinessServiceException {
		try {
			securedGenericCategoryService.remove(metadata, type);
		} catch (Exception e) {
			throw new GNUOpenBusinessServiceException(e.getMessage(), e);
		}
	}
}