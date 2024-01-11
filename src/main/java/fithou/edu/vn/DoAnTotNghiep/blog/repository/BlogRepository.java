package fithou.edu.vn.DoAnTotNghiep.blog.repository;

import fithou.edu.vn.DoAnTotNghiep.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {

}
