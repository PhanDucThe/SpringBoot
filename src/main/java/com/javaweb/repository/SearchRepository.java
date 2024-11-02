package com.javaweb.repository;

import com.javaweb.dto.response.PageResponse;
import com.javaweb.dto.response.UserDetailResponse;
import com.javaweb.model.tbl_user;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class SearchRepository {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    public PageResponse<List<UserDetailResponse>> getUserAllWithSortAndSearchRepository(int pageNo, int pageSize, String search, String sortField) {
        // query list user
        // query so recode theo dk tim kiem
        StringBuilder sql = new StringBuilder("select * from tbl_user u where 1=1 ");
        if (StringUtils.hasLength(search)) {
            sql.append(" and (");
            sql.append(" lower(u.first_name) like lower(:firstName) ");
            sql.append(" or lower(u.last_name) like lower(:lastName) ");
            sql.append(" or lower(u.email) like lower(:email) ");
            sql.append(")");
        }

        if (StringUtils.hasLength(sortField)) {
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
            Matcher matcher = pattern.matcher(sortField);
            if (matcher.find()) {
                sql.append(String.format("order by u.%s %s", matcher.group(1), matcher.group(3)));
            }
        }

        Query query = entityManager.createNativeQuery(sql.toString(), tbl_user.class);

        // Sau khi lay du lieu xong thi phai phan trang de ma do data len chu
        // khong phai do len mot dong nhu vay
        int p = pageNo;
        if (pageNo != 0 && pageSize != 1) {
            p = pageNo - 1;
        }
        query.setFirstResult(p * pageSize);
        query.setMaxResults(pageSize);

        if (StringUtils.hasLength(search)) {
            query.setParameter("firstName", String.format("%%%s%%", search));
            query.setParameter("lastName", String.format("%%%s%%", search));
            query.setParameter("email", String.format("%%%s%%", search));
        }

        List<tbl_user> users = query.getResultList();

        StringBuilder sqlQuery = new StringBuilder("select count(*) from tbl_user u where 1=1 ");
        if (StringUtils.hasLength(search)) {
            sqlQuery.append(" and (");
            sqlQuery.append(" lower(u.first_name) like lower(?1) ");
            sqlQuery.append(" or lower(u.last_name) like lower(?2) ");
            sqlQuery.append(" or lower(u.email) like lower(?3) ");
            sqlQuery.append(")");
        }
        Query query1 = entityManager.createNativeQuery(sqlQuery.toString());
        if (StringUtils.hasLength(search)) {
            query1.setParameter(1, String.format("%%%s%%", search));
            query1.setParameter(2, String.format("%%%s%%", search));
            query1.setParameter(3, String.format("%%%s%%", search));
        }

        Long totalelements = (Long) query1.getSingleResult();

        PageResponse<List<UserDetailResponse>> pageResponse = new PageResponse<>();
        List<UserDetailResponse> userDetailResponseList = new ArrayList<>();
        pageResponse.setPageNo(pageNo);
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotalPage(totalelements.intValue()/pageSize);
        for (tbl_user user : users) {
            UserDetailResponse userDetailResponse = new UserDetailResponse();
            modelMapper.map(user, userDetailResponse);
            userDetailResponseList.add(userDetailResponse);
        }
        pageResponse.setItem(userDetailResponseList);

        return pageResponse;
    }
}
