package com.qsoft.OnlineDio.Model;

/**
 * User: khiemvx
 * Date: 10/14/13
 */
public class HomeModel
{
    private int idImage;
    private String title;
    private String person;
    private int like;
    private int comment;
    private String time;

    public HomeModel(int idImage, String title, String person, int like, int comment, String time)
    {
        this.idImage = idImage;
        this.title = title;
        this.person = person;
        this.comment = comment;
        this.like = like;
        this.time = time;
    }

    public int getIdImage()
    {
        return idImage;
    }

    public void setIdImage(int idImage)
    {
        this.idImage = idImage;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPerson()
    {
        return person;
    }

    public void setPerson(String person)
    {
        this.person = person;
    }

    public int getLike()
    {
        return like;
    }

    public void setLike(int like)
    {
        this.like = like;
    }

    public int getComment()
    {
        return comment;
    }

    public void setComment(int comment)
    {
        this.comment = comment;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
}
