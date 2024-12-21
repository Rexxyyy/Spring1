//package com.ecom.demo.eecom.Controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.ecom.demo.eecom.pojo.CommentApiDetails;
//import org.springframework.web.bind.annotation.RestController;
//import com.ecom.demo.eecom.service.CommentService;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
////_________________________________________________________________________________//
//
//@RestController
//@RequestMapping("/comments")
//public class CommentController {
//
//    @Autowired
//    public CommentService commentService;
//
//    // Add comment
//    @PostMapping("/comments")
//    public String addComment(@RequestBody CommentApiDetails commentApiDetails) {
//        if (commentApiDetails.getComment() == null || commentApiDetails.getComment().isEmpty()) {
//            return "Comment cannot be null or empty";
//        }
//        return commentService.comments(commentApiDetails);
//    }
//
//    @PutMapping("/comments/{commentId}")
//    public String updateComment(@RequestBody CommentApiDetails commentApiDetails,@PathVariable String commentId) {
//        if (commentApiDetails.getComment() == null || commentApiDetails.getComment().isEmpty()) {
//            return "Update failed: Comment cannot be null or empty.";
//        }
//        return commentService.updateComment(commentApiDetails);
//    }
//
//@DeleteMapping("/comments/{commentId}")
//    public String deleteComment(@RequestBody CommentApiDetails commentApiDetails, @PathVariable String commentId){
//        if(commentApiDetails.getCommentID()== null || commentApiDetails.getCommentID().trim().isEmpty()){
//            return "Delete failed: as commentID doesn't exits";
//        }
//        if(commentApiDetails.getComment()== null || commentApiDetails.getComment().isEmpty()){
//            return "Delete failed: as comment doesnt exits";
//        } return "Comment Deleted Successfully: " + commentApiDetails.getComment();
//
//
//    }
//
//    @PatchMapping ("/comments/{commentId}")
//    public String patchComment(@RequestBody CommentApiDetails commentApiDetails, @PathVariable String commentId){
//        if(commentApiDetails.getCommentID()== null || commentApiDetails.getCommentID().trim().isEmpty()){
//            return "Patch failed: as commentID doesn't exits";
//        }
//        if(commentApiDetails.getComment()== null || commentApiDetails.getComment().isEmpty()){
//            return "Patch failed: as comment doesnt exits";
//        } return "Comment Patched Successfully: " + commentApiDetails.getComment();
//
//    }
//
//}
