package com.krish.writeopia.business_layer;

import com.krish.writeopia.models.Blogs;
import com.krish.writeopia.models.BlogsLikes;
import com.krish.writeopia.repository.BlogsLikesRepository;
import com.krish.writeopia.repository.BlogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class BlogsService {
    @Autowired
    BlogsRepository blogsRepository;
    @Autowired
    BlogsLikesRepository blogsLikesRepository;

    // Blog Services
    public Blogs getBlogByIdService(Long id) {
        return blogsRepository.findBlogsById(id);
    }

    public List<Blogs> getAllBlogs() {
        List<Blogs> allBlogs = new ArrayList<>();
        blogsRepository.findAll().forEach(allBlogs::add);
        return allBlogs;
    }

    public List<Blogs> getUserBlogs(String userName) {
        List<Blogs> allUserBlogs = new ArrayList<>();
        blogsRepository.findAllByMyUsers_UserName(userName).forEach(allUserBlogs::add);
        return allUserBlogs;
    }

    public void addBlog(Blogs blog) {
        blogsRepository.save(blog);
    }

    // BlogLikes Services
    public BlogsLikes blogLikeStatus(Long blogId, String userName, String userNameSame){
        return blogsLikesRepository
                .findBlogsLikesByBlog_IdAndLikedBy_UserNameOrUnlikedBy_UserName(blogId, userName, userNameSame);
    }
    public void addLikeUnlikeService(BlogsLikes bloglike) {
        blogsLikesRepository.save(bloglike);
    }
    public void removeLikeUnlikeService(Long id) {
        blogsLikesRepository.deleteById(id);
    }


    public Integer noOfLikes(Long blogId) {
        AtomicReference<Integer> count = new AtomicReference<>(0);
        blogsLikesRepository.findAllByBlog_Id(blogId)
                .forEach((blogsLikes) -> {
                    if (blogsLikes.getLikedBy() != null) {
                        count.updateAndGet(v -> v + 1);
                    }
                });
        return count.get();
    }

    public Integer noOfUnLikes(Long blogId) {
        AtomicReference<Integer> count = new AtomicReference<>(0);
        blogsLikesRepository.findAllByBlog_Id(blogId)
                .forEach((blogsLikes) -> {
                    if (blogsLikes.getUnlikedBy() != null) {
                        count.updateAndGet(v -> v + 1);
                    }
                });
        return count.get();
    }

    public List<BlogsLikes> allBlogLikes() {
        return blogsLikesRepository.findAll();
    }

}
