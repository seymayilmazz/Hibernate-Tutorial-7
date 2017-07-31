package com.codegirl;

import com.codegirl.model.Comment;
import com.codegirl.model.Post;
import com.codegirl.model.PostDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by Şeyma Yılmaz on 31.7.2017.
 */
public class HibernateTest {

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory= configuration.buildSessionFactory();
        Session session=sessionFactory.openSession();

        session.beginTransaction();

        /* post */
        Post post = new Post();
        post.setTitle("Yaş günü kutlaması");
        post.setContent("Hepinize çok teşekkür ederim :)");

        /* post details */
        PostDetails postDetails = new PostDetails();
        postDetails.setCreatedBy("Burak");

        try {
            Date date = dateFormat.parse("03/07/2011");
            postDetails.setCreatOn(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        postDetails.setLocation("Sivas");


        post.setPostDetails(postDetails);
        postDetails.setPost(post);

        Comment comment1=new Comment();
        comment1.setText("çok güzel çıkmışsın");
        comment1.setCreatedBy("BuSE");
        comment1.setPost(post);
        try {
            Date date = dateFormat.parse("02/07/2016");
            comment1.setCreateOn(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Comment comment2=new Comment();
        comment2.setText("çok güzel çıkmışsın");
        comment2.setCreatedBy("BuSE");
        comment2.setPost(post);
        try {
            Date date = dateFormat.parse("02/07/2016");
            comment2.setCreateOn(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        post.getCommentList().add(comment1);
        post.getCommentList().add(comment2);

        session.save(post);
        session.save(postDetails);
        session.save(comment1);
        session.save(comment2);

        Post post1 = session.find(Post.class, 1L);

        session.getTransaction().commit();
        session.close();

    }
}
