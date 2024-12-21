//package com.ecom.demo.eecom.service;
//import org.springframework.stereotype.Service;
//import com.ecom.demo.eecom.pojo.CommentApiDetails;
//
//@Service
//public class CommentService {
//
//	public String comments(CommentApiDetails commentApiDetails) {
//		return "Comment Added Successfully: " + commentApiDetails.getComment();
//	}
//
//	// Update comment SERVICE
//	public String updateComment(CommentApiDetails commentApiDetails) {
//		if (commentApiDetails.getComment() == null || commentApiDetails.getComment().isEmpty()) {
//			return "Update failed: Comment cannot be null or empty.";
//		}
//		return "Comment Updated Successfully: " + commentApiDetails.getComment();
//	}
//
//	// Delete comment SERVICE
//	public String deleteComment(CommentApiDetails commentApiDetails) {
//		if (commentApiDetails.getCommentID() == null || commentApiDetails.getCommentID().trim().isEmpty()) {
//			return "Delete failed: as commentID doesn't exits";
//		}
//		if (commentApiDetails.getComment() == null || commentApiDetails.getComment().trim().isEmpty()) {
//			return "Delete failed: as comment doesn't exits";
//		}
//		return "Comment Deleted Successfully: " + commentApiDetails.getComment();
//	}
//
//	// Patch comment SERVICE
//	public String patchComment(CommentApiDetails commentApiDetails) {
//		if (commentApiDetails.getCommentID() == null || commentApiDetails.getCommentID().trim().isEmpty()) {
//			return "Patch failed: as commentID doesn't exits";
//		}
//		if (commentApiDetails.getComment() == null || commentApiDetails.getComment().trim().isEmpty()) {
//			return "Patch failed: as comment doesn't exits";
//		}
//		return "Comment Patched Successfully: " + commentApiDetails.getComment();
//
//	}
//}