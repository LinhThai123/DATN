package fithou.edu.vn.DoAnTotNghiep.blog.service.impl;

import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.blog.repository.BlogRepository;
import fithou.edu.vn.DoAnTotNghiep.blog.service.BlogService;
import fithou.edu.vn.DoAnTotNghiep.common.cqrs.HandleResponse;
import fithou.edu.vn.DoAnTotNghiep.config.Contant;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Blog getBlogBySlug(String slug) throws NotFoundException {
        Optional<Blog> rs = blogRepository.findBySlug(slug);
        if (!rs.isPresent()) {
            throw new NotFoundException("Không tìm thấy bài viết");
        }
        return rs.get();
    }
}
