// pages/learn/learn.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    word_list:"",
    pron_list:"",
    tran_list:"",
    word:"",
    pron:"",
    tran:"",
    show:"",
    count:0,
    complete:false,
    complete_count:0,
    new_study:false,
    isnotcomplete:true,
    iscomplete:true
  },
  cancel1: function(){  
    this.setData({  
      isnotcomplete:true
    });  
  },
  cancel2: function(){  
    this.setData({  
      iscomplete:true
    });  
  },
  confirm1: function(){  
    this.setData({  
      isnotcomplete:true
    });  
  },
  confirm2: function(){  
    this.setData({
      iscomplete:true
    });  
  },
  button_begin:function(){
    var that=this
    var word=that.data.word_list[that.data.count]
    var pron=that.data.pron_list[that.data.count]
    var tran=that.data.tran_list[that.data.count]
    that.setData({
      word:word,
      pron:pron,
      tran:tran,
      show:false,
      new_study:true,
      complete_count:that.data.word_list.length
    })
  },
  button_miss: function(){
    var that=this
    that.setData({
      show:true,
      complete_count:that.data.complete_count-1
    })
  },
  button_next: function(){
    var that=this
    var newcount=that.data.count+1
    if(newcount<that.data.word_list.length){
      var word=that.data.word_list[newcount]
      var pron=that.data.pron_list[newcount]
      var tran=that.data.tran_list[newcount]
      that.setData({
        word:word,
        pron:pron,
        tran:tran,
        show:false,
        count:newcount
      })
    }
    else{
      if(that.data.complete_count!=that.data.word_list.length){
        console.log("not complete")
        that.setData({
          complete:false,
          count:0,
          isnotcomplete:false,
          new_study:false
        })
      }
      else{
        console.log("complete")
        that.setData({
          complete:true,
          count:0,
          iscomplete:false,
          new_study:false
        })
      }
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var app=getApp()
    var that=this
    wx.request({
      url: 'http://localhost:8083/jbyEng/getReviewWord',
      data:{
        id:app.globalData.user_id,
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log("successs")
        if(res.data==''){
          wx.navigateBack({})
        }
        that.setData({
          word_list:res.data.单词,
          pron_list:res.data.发音,
          tran_list:res.data.翻译
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    var that=this
    var app=getApp()
    if(that.data.complete){
      wx.request({
        url: 'http://localhost:8083/jbyEng/getReviewNum',
        data:{
          id:app.globalData.user_id,
          num:that.data.complete_count
        },
        method: 'GET',
        header: {
          'content-type': 'application/json'
        },
        success: function (res) {
          console.log("successs")
        }
      })
    }
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})