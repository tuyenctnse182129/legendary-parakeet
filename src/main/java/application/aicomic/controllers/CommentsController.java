package application.aicomic.controllers;

import application.aicomic.dataAccess.CommentsDTO;
import application.aicomic.dataAccess.CurrentChapterDTO;
import application.aicomic.models.Comments;
import application.aicomic.models.CurrentChapter;
import application.aicomic.services.CommentsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comment")
@RestController
public class CommentsController {
    private  CommentsService commentsService;

    @GetMapping("/getAll")
    public List<Comments> getAllComments() {
        return commentsService.getAllComments();
    }

    @PostMapping("/post")
    public Comments addComment(@RequestBody Comments comments) {
        return commentsService.addComment(comments);
    }

    @PutMapping("/update")
    public Comments updateComment(@PathVariable String id, @RequestBody CommentsDTO commentsDTO) {
        return commentsService.updateComment(id, commentsDTO);
    }
    @GetMapping("/getById")
    public Comments getCommentById(@PathVariable String id) {
        return commentsService.getCommentById(id);
    }

    @DeleteMapping("/delete")
    public Comments deleteComment(@PathVariable String id) {
        return commentsService.deleteComment(id);
    }
}
