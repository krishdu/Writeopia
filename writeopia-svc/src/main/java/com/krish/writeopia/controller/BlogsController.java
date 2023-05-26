package com.krish.writeopia.controller;

import com.krish.writeopia.business_layer.BlogsService;
import com.krish.writeopia.models.Blogs;
import com.krish.writeopia.models.BlogsLikes;
import com.krish.writeopia.business_layer.MyUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Blog Controller
 */
@RestController
public class BlogsController {
    @Autowired
    private BlogsService blogsService;
    @Autowired
    private MyUsersService myUsersService;

    @GetMapping("/blogs/show/{id}")
    public Blogs showABlogById(@PathVariable Long id){
        return blogsService.getBlogByIdService(id);
    }

    @GetMapping("/blogs")
    public List<Blogs> showAllBlogs(){
        return blogsService.getAllBlogs();
    }

    @GetMapping("/blogs/{userName}")
    public List<Blogs> showAllUserBlogs(@PathVariable String userName){
        return blogsService.getUserBlogs(userName);
    }

    @PostMapping("/blogs/add")
    public void addBlog(@RequestBody Blogs blog){
        blogsService.addBlog(blog);
    }

    /**
     * Get Like Status of User For A Particular Blog
     */
    @GetMapping("/blogs/{id}/{userName}")
    BlogsLikes getBlogUserLikeStatus(@PathVariable Long id, @PathVariable String userName){
        return blogsService.blogLikeStatus(id, userName, userName);
    }

    /**
     *  Add/Modify User Like On A Blog
     */
    @PostMapping("/blogs/likeunlike/")
    void addLikeUnlike(@RequestBody BlogsLikes blogsLikes){
        blogsService.addLikeUnlikeService(blogsLikes);
    }

    /**
     *  Remove User Like On A Blog
     */
    @PostMapping("/blogs/likeunlike/remove/{id}")
    void removeLikeUnlike(@PathVariable Long id){
        blogsService.removeLikeUnlikeService(id);
    }

    /**
     * No Of Unlikes Received By A Blog
     */
    @GetMapping("/blogs/likes/{id}")
    Integer noOfLikesBlog(@PathVariable Long id){
        return blogsService.noOfLikes(id);
    }

    /**
     *  No Of Likes Received By A Blog
     */
    @GetMapping("/blogs/unlikes/{id}")
    Integer noOfUnlikesBlog(@PathVariable Long id){
        return blogsService.noOfUnLikes(id);
    }

    /**
     * // Get All BlogLikes Objects
     */
    @GetMapping("/blogs/get")
    List<BlogsLikes> getAllBlogLikes(){
        return blogsService.allBlogLikes();
    }
}
