package fithou.edu.vn.DoAnTotNghiep.blog.service;

import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.product.entity.Capacity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {
    List<Blog> getListBlog() ;
    Page<Blog> adminGetListBlog(String title, int page);
}