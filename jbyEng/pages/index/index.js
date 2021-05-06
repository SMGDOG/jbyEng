// pages/index/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    day_num:"",
    table:"",
    word_list:"",
    tran_list:"",
    pron_list:""
  },
  input: function (e) {
    this.setData({
      day_num:e.detail.value
    })
  },
  button1: function(){
    var app=getApp()
    var that=this
    wx.request({
      url: 'http://localhost:8083/jbyEng/learn',
      data:{
        id:app.globalData.user_id,
        num:that.data.day_num,
        table_name:"CET4"
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log("successs")
      }
    })
  },
  button2: function(){
    var app=getApp()
    var that=this
    wx.request({
      url: 'http://localhost:8083/jbyEng/learn',
      data:{
        id:app.globalData.user_id,
        num:that.data.day_num,
        table_name:"CET6"
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log("successs")
      }
    })
  },
  button3: function(){
    var app=getApp()
    var that=this
    wx.request({
      url: 'http://localhost:8083/jbyEng/resetnum',
      data:{
        id:app.globalData.user_id,
        num:that.data.day_num
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log("successs")
      }
    })
  },
  button4: function(){
    wx.navigateTo({
      url: '/pages/learn/learn',
    })
  },
  button5: function(){
    var app=getApp()
    wx.request({
      url: 'http://localhost:8083/jbyEng/reset',
      data:{
        id:app.globalData.user_id,
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log("successs")
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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