package fithou.edu.vn.DoAnTotNghiep.blog.service.impl;

import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.blog.repository.BlogRepository;
import fithou.edu.vn.DoAnTotNghiep.blog.service.BlogService;
import fithou.edu.vn.DoAnTotNghiep.config.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Override
    public List<Blog> getListBlog() {
        return blogRepository.findAll();
    }

    @Override
    public Page<Blog> adminGetListBlog(String title, int page) {
        page--;
        if (page <= 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Contant.LIMIT_BLOG , Sort.by("createdDate").descending());
        return blogRepository.findAllByTitleContainingIgnoreCase( title,  pageable);
    }
}
