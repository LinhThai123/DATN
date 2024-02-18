package fithou.edu.vn.DoAnTotNghiep.blog.repository;

import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import fithou.edu.vn.DoAnTotNghiep.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {
    Page<Blog> findAllByTitleContainingIgnoreCase(String title, org.springframework.data.domain.Pageable pageable);

}
