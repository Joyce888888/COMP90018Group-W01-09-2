package com.example.comp90018.Activity.Shake;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp90018.R;

import java.util.ArrayList;

//public class MomentAdapter extends BaseAdapter {
//    private Context context;
//    private ArrayList<Moment> feed_array;
//    private String finalLikeText;
//    private String tmpLike;
//    private int likePosition;
//
//
//    public MomentAdapter(Context c, ArrayList<Moment> data) {
//        context = c;
//        feed_array = data;
//    }
//
//    //setter method to pass the data from fragment to here
//    public void setFeed_array(ArrayList<Moment> feed_array) {
//        this.feed_array = feed_array;
//    }
//
//    //this method updates the list view when the array has been changed
//    @Override
//    public void notifyDataSetChanged() {
//        super.notifyDataSetChanged();
//    }
//
//    @Override
//    public int getCount() {
//        if(feed_array != null) {
//            return feed_array.size();
//        } else {
//            return 0;
//        }
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    //@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    @Override
//    //set the view of the list view in home page fragment
//    public View getView(final int position, View view, ViewGroup viewGroup) {
//        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View rowView = view.inflate(UserViewActivity.this, R.layout.feed, null);
//
//        //get the feed with location
//        final Moment oneFeed = feed_array.get(position);
//
//        //find all UI elements
//        ImageView userProfileImg = (ImageView) rowView.findViewById(R.id.userImage);
//        TextView userName = (TextView) rowView.findViewById(R.id.text_userName);
//        TextView locationName = (TextView) rowView.findViewById(R.id.locationTextView);
//        ImageView photoImg = (ImageView) rowView.findViewById(R.id.photoImage);
//        final TextView likedText = (TextView) rowView.findViewById(R.id.likedTextView);
//        TextView commentText = (TextView) rowView.findViewById(R.id.commentTextView);
//        final ImageButton likeButton = (ImageButton) rowView.findViewById(R.id.likeButton);
//        final ImageButton commentButton = (ImageButton) rowView.findViewById(R.id.commentButton);
//        TextView captionText = (TextView) rowView.findViewById(R.id.captionTextView);
//
//        //set text view styles
//        TextView captionFixText = (TextView) rowView.findViewById(R.id.captionText);
//        captionFixText.setTypeface(captionFixText.getTypeface(), Typeface.BOLD);
//        TextView likesFixText = (TextView) rowView.findViewById(R.id.likedText);
//        likesFixText.setTypeface(likesFixText.getTypeface(), Typeface.BOLD);
//        TextView commentFixText = (TextView) rowView.findViewById(R.id.commentText);
//        commentFixText.setTypeface(commentFixText.getTypeface(), Typeface.BOLD);
//
//        //set user profile image, user name, location name, photo image
////        userProfileImg.setImageBitmap(oneFeed.getUserProfileImg());
//        userName.setText(oneFeed.getUsername());
//        locationName.setText(oneFeed.getLocation());
//        captionText.setText(oneFeed.getContext());
////        photoImg.setImageBitmap(oneFeed.getPhoto());
//
//        //set visibility for button
////        if (oneFeed.getCaption() != null && oneFeed.getCaption().equals("#In Range")){
////            likeButton.setVisibility(View.GONE);
////            commentButton.setVisibility(View.GONE);
////            likesFixText.setVisibility(View.GONE);
////            commentFixText.setVisibility(View.GONE);
////            likedText.setVisibility(View.GONE);
////            commentText.setVisibility(View.GONE);
////        }
////
////        // implement the like function here. POST request to Instagram API
////        likeButton.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View arg0) {
////                if (!oneFeed.getUser_has_liked()) {
////                    likeButton.setBackground(rowView.getResources().getDrawable(R.drawable.filled_heart));
////                    oneFeed.setUser_has_liked(true);
////                    tmpLike = likedText.getText().toString();
////                    likePosition = position;
////                    String url = context.getResources().getString(R.string.instagram_api_url)
////                            + context.getResources().getString(R.string.instagram_api_media_method)
////                            + oneFeed.getMediaID().toString()
////                            + "/likes?access_token="
////                            + context.getResources().getString(R.string.instagram_access_token);
////                    //update liked list
////                    Toast.makeText(context.getApplicationContext(), "You liked!", Toast.LENGTH_LONG).show();
////                    System.out.println("Like: " + tmpLike);
////                    //updating the view
////                    if (tmpLike.equals(oneFeed.getLike().toString())) {
////                        if (tmpLike.length() > 0) {
////                            if (Character.isDigit(tmpLike.charAt(1))) {
////                                int likeNum = Integer.parseInt(tmpLike.replaceAll("[^0-9]", "")) + 1;
////                                finalLikeText = "[" + String.valueOf(likeNum) + " likes]";
////                            } else {
////                                finalLikeText = tmpLike.replace("]", "") + ", carl_xs]";
////                            }
////                        }
////                        likedText.setText(finalLikeText);
////                    }
////                    System.out.println("Like: " + finalLikeText);
////                } else {
////                    Toast.makeText(context, "You have already liked!", Toast.LENGTH_LONG).show();
////                }
////            }
////        });
////
////        // implement the comment function here through comment activity
////        commentButton.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View arg0) {
////                Intent intent = new Intent(context, CommentActivity.class);
////                context.startActivity(intent);
////            }
////        });
////
////        // set up the blank caption text in the view
////        if (oneFeed.getCaption() != null) {
////            captionText.setText(oneFeed.getCaption());
////        } else {
////            captionText.setText("No caption.");
////        }
////
////        // set up the blank like text in the view
//        if (oneFeed.getLikelist() != null) {
//            likedText.setText(oneFeed.getLikelist().toString().replace(',', ' '));
//        } else {
//            likedText.setText("Nobody has liked yet.");
//        }
//
//        // set up the blank comment text in the view
//        if (oneFeed.getComment() != null) {
//            commentText.setText(oneFeed.getComment().toString().replace(',', ' ').substring(1,
//                    oneFeed.getComment().toString().length() - 1));
//        } else {
//            commentText.setText("Nobody has commented yet.");
//        }
//        return rowView;
//    }
//
//}