// pages/user/user.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:"",
    name:"",
    learn_table:"",
    learn_num:"",
    learn_sum:""
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
    var that=this
    var app=getApp()
    wx.request({
      url: 'http://localhost:8083/jbyEng/getLearnInfo',
      data:{
        id:app.globalData.user_id
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log("success")
        var id=app.globalData.user_id
        var name=app.globalData.user_name
        var table=res.data.正在学习
        var num=res.data.每日学习量
        var sum=res.data.已学习数量
        if(table==null){
          table="无"
        }
        that.setData({
            id:id,
            name:name,
            learn_table:table,
            learn_num:num,
            learn_sum:sum
        })
      }
    })
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