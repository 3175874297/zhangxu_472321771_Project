<template>
  <div class="root">
    <div class="top">
      <div class="top1" ref="lexicalForm">
        <textarea class="content" placeholder="请输入表达式（文法之间以空格隔开）" v-model="LRContent.content" cols="30" rows="10"></textarea>
        <div class="but1">
          <el-row>
            <el-button type="primary" round @click="submit()" >一键分析</el-button>
          </el-row>
        </div>
      </div>
      <div class="top2">
        <table border="1" class="table2">
          <tr class="Header">
            <td>
              <!-- style="height:29px; width:294px; border-bottom:1px solid black;" -->
              <div class="STATE" style="height:68px;">状态</div>
              <div  class="STATE" style="height:35px;" v-for="(item,index) in LRContentFrom.tableContent1" :key="index"><div v-if="item[0]===''">暂无数据</div>{{item[0]}}</div>
              </td>
            <td class="ACTION">
              <tr>
                <div class="actionChild">ACTION</div>
              </tr>
              <tr class="actionChildHeaderFather"><div class="actionChildHeader" v-for="(item,index) in LRContentFrom.tableHeader1" :key="index">{{item}}</div></tr>
              <tr class="actionChildHeaderFather" v-for="(item,index) in LRContentFrom.tableContent1" :key="index">
                <div class="actionChildHeader" v-for="count in (limit)" :key="count">{{item[count]}}</div>
              </tr>
            </td>
            <td class="gotoChild">
               <tr><div class="gotoChild">GOTO</div></tr>
               <tr class="gotoChildHeaderFather"><div class="gotoChildHeader" v-for="(item,index) in LRContentFrom.tableHeader2" :key="index">{{item}}</div></tr>
              <tr class="gotoChildHeaderFather" v-for="(item,index) in LRContentFrom.tableContent2" :key="index">
                <div class="gotoChildHeader" v-for="(it,ind) in item" :key="ind"><div v-if="it===' '" style="height:35px"></div>{{it}}</div>
              </tr>
            </td>
          </tr>
          <!-- <tr :class="count>limit ? 'gotoChildHeaderFather' : 'actionChildHeaderFather'" v-for="(item,index) in LRContentFrom.tableContent" :key="index">
             <td v-for="count in item.length" :key="count" >
               <div class="STATE" v-if="(count-1)===0" >{{item[0]}}</div>
                 <div class="actionChildHeader" v-if="(count>1)&&(count<limit+1)">{{item[count-1]}}
                   </div>
                  <div class="gotoChildHeader" v-if="count>limit">{{item[count-1]}}</div>
             </td>
          </tr> -->
        </table>
      </div>
    </div> 
    <div class="middle">
      <table class="table1" border="1" style="font-size:20px ">
        <td>
          <tr><div style="width:140px; float:left">非终结符：</div><div class="box">{{LRContentFrom.VN}}</div></tr> 
          <tr><div style="width:140px; float:left">终结符：</div><div class="box">{{LRContentFrom.VT}}</div></tr> 
          <tr><div style="width:140px; float:left">拓广文法：</div><div class="box" v-for="(item,index) in LRContentFrom.DFAFunc" :key="index">{{item}}</div></tr>
          <tr><div style="width:140px; float:left">开始符：</div><div class="box">{{LRContentFrom.start}}</div></tr>
          <tr><div style="width:140px; float:left">状态转换函数：</div><div class="box" v-for="(item,index) in LRContentFrom.expand" :key=index>{{item}}</div></tr>
        </td>
      </table>
    </div>
    <div class="bottom">
        <div class="bottom0 top0">

        </div>
        <div class="bottom1 top1" ref="lexicalForm">
          <textarea class="content" placeholder="" v-model="LRExpression.expression" cols="30" rows="10">
          </textarea>
          <div class="bott1 but1">
            <el-row>
              <el-button type="primary" round @click="submit2()" >一键提交</el-button>
            </el-row>
          </div>
        </div>
        <div class="bottom2 top2">
            <table border="1" align="center" style="margin:auto; text-align:center;">
            <div class="aaa">
          <p style="font-size:40px;height:60px">计算过程</p>
        </div>
        <div>
          <tr style="font-size:25px; text-align:center; height:40px;">
           <td style="width:200px;height:30px;">步骤索引</td>
           <td style="width:200px;height:30px;">状态栈</td>
           <td style="width:200px;height:30px;">符号栈</td>
           <td style="width:200px;height:30px;">输入串</td>
         </tr>
        </div>
         
         <div>
           <tr v-for="(content,index) in LRExpressionFrom.process" :key="index" >
             <td style="width:200px; font-size:25px">{{index+1}}</td>
            <td style="width:200px; font-size:25px" v-for="(con,ind) in content" :key="ind">{{con}}</td>
          </tr>
         </div>
          
        </table>
        </div>
    </div>
    <div class="end">
        <!-- <table border="1">
          <tr style="font-size:25px">
            <th style="width:200px; height:60px">文中符号</th>
            <th style="width:300px; height:60px">类型</th>
            <th style="width:520px; height:60px">对应DFA</th>
            <th style="width:480px; height:60px">错误</th>
            <th style="width:480px; height:60px">处理</th>
          </tr>
      </table> -->
    </div>
  </div>
</template>

<script>
export default {
  name:'LRAnalysis',
  data() {
    return {
      LRContent:{
        content:''
      },
      LRExpression:{
        expression:''
      },
      LRContentFrom:{
        VN:'暂无数据',
        VT:'暂无数据',
        expand:['暂无数据'],
        judge:true,
        start:'暂无数据',
        DFAFunc:['暂无数据'],
        tableHeader1:['暂无数据'],
        tableHeader2:['暂无数据'],
        tableContent1:[['']],
        tableContent2:[['']],
        tableContent:[['']]
        // index:1
      },
      Temp:[''],
      Temp2:[''],
      limit:0,
      LRExpressionFrom:{
        process:[['']],
        judge:true
      },
      point:0
    }
  },
  methods:{
    submit(){
        if(this.LRContent.content===""){
          alert('提交内容为空，请重新输入')
          return
        }
          console.log(this.LRContent.content)
        this.$http({
            url:`LR/LRContent`,
            method: "post",
            data: this.LRContent
        }).then(({ data }) => {
          if(data.code==='200'){
              this.$message('数据输入错误，请重新输入')
               return
            }
            if (data!==null) {
                this.$message({
                    message: "操作成功",
                    type: "success",
                    duration: 1500,
                });
                this.point++;
                 this.LRContentFrom.VN=data.vn,
                 this.LRContentFrom.VT=data.vt,
                 this.LRContentFrom.expand=data.expand.split(' '),
                 this.LRContentFrom.judge=data.judge,
                 this.LRContentFrom.start=data.start,
                 this.LRContentFrom.DFAFunc=data.dfafunc.split(' '),
                 this.Temp=data.tableHeader.split('#'),
                 this.Temp2=this.Temp[0].trim()+' #',
                 this.LRContentFrom.tableHeader1=this.Temp2.split(' '),
                 this.limit=this.LRContentFrom.tableHeader1.length
                 this.LRContentFrom.tableHeader2=this.Temp[1].trim().split(' '),
                 console.log(this.LRContentFrom.tableHeader2)
                 for(let i=0;i<data.tableContent.length;i++){
                   this.$set(this.LRContentFrom.tableContent,i,data.tableContent[i].split('.'))
                      // this.LRContentFrom.tableContent[i]=data.tableContent[i].split('.')
                      this.LRContentFrom.tableContent1[i]=this.LRContentFrom.tableContent[i].slice(0,this.limit+1)
                      // let l=
                      this.LRContentFrom.tableContent2[i]=this.LRContentFrom.tableContent[i].slice(this.limit+1,(data.tableContent.length-this.limit+2))
                      console.log(this.LRContentFrom.tableContent2[i])
                 }
                 console.log(this.LRContentFrom.tableContent1)
            } else {
                this.$message.error(data.msg);
            }
            console.log(this.LRContentFrom.content)
        });
        
    },
    submit2(){
      if(this.LRExpression.expression===""){
          alert('提交内容为空，请重新输入')
          return
        }
        if(this.point===0){
           this.$message('不能进行LR(0)分析演示，需输入正确文法！！！')
           return
        }
        this.$http({
            url:`LR/LRExpression`,
            method: "post",
            data: this.LRExpression
        }).then(({ data }) => {
          if(data.code==='200'){
              this.$message('数据输入错误，请重新输入')
               return
            }
            if (data!==null) {
                this.$message({
                    message: "操作成功",
                    type: "success",
                    duration: 1500,
                });
                for(let i=0;i<data.process.length;i++){
                   this.$set(this.LRExpressionFrom.process,i,data.process[i].split(' '))
                  // this.LRExpressionFrom.process[i]=data.process[i].split(' ')
                }
                this.LRExpressionFrom.judge=data.judge
            } else {
                this.$message.error(data.msg);
            }
            console.log(this.content)
        });
    }
  },
  mounted(){

  }
}
</script>
<style lang="css" scoped>
.root{
  height: 100%;
  width: 2020px;
  background-color:transparent;
}
.top{
  margin-top: 0;
  margin-left: 0;
  position: relative;
  width: 2020px;
  background-color: transparent;
  top: 0;
  left: 0;
  height: 480px;
}
.middle{
  position: relative;
  margin-top: 78px;
  width: 2007px;
  background-color: #d9ecff;
}
.bottom{
  position: relative;
  margin-top: 10px;
  background-color: #d9ecff;
  width: 2007px;
}
.end{
  position: relative;
  margin-top: 570px;
  width: 2007px;
  background-color: #d9ecff;
}
/*最外层透明*/
::v-deep .el-table,
::v-deep .el-table__expanded-cell {

  background-color: transparent !important;
}
/* 表格内背景颜色 */
::v-deep .el-table th,
::v-deep .el-table tr,
::v-deep .el-table td {
  background-color: transparent !important;
  border: 0;
}
.top0{
  position: relative;
  float: left;
  margin-top: 0;
  margin-left: 0;
  width: 620px;
  height: 550px;
  border-bottom-right-radius: 20px;
  background-color: #d9ecff;
  top: 0;
  left: 0;
}
.top1{
  position: absolute;
  margin-top: 0;
  height: 550px;
  margin-left: 0;
  width: 980px;
  border-bottom-right-radius: 20px;
  background-color:#d9ecff;
  top: 0;
  left: 0;
}

.top2{
  position: absolute;
  width: 980px;
  height: 550px;
  margin-top: 0px;
  margin-left: 1028px;
  background-color:#d9ecff;
  padding-top: 0px;
  border-bottom-left-radius: 20px;
  top: 0;
}
label{
  width: 90px;
}
textarea{
  resize: none;
}
.content{
  width: 930px;
  height: 440px;
  font-size: 25px;
  margin-top: 1%;
  margin-left: 2%;
  resize:none;
  border-bottom-left-radius: 25px;
  border-bottom-right-radius: 25px;
}
.but1 {
  position: relative;
  margin-top: 2%;
  top: 3%;
  left: 41%;
}
.initialBox{
  padding-top: 85px;
}
.endBox{
  padding-top: 13px;
}
.exchange{
  width: 500px;
  height: 70px;
}
.box{
   width: 300px;
  float: left;
  border: 1px solid black;
  margin: 2px;
  font-size: 20px;
  text-align: center;
  height: 60px;
  line-height: 60px;
  /* width: 200px;
  font-size: 20px;
  text-align: center;
  line-height: 60px;
  height: 60px;
  border:solid 1px black; */
}
.box2{
width: 300px;
}
.box3{
  width: 520px;
}
.box4{
  width: 480px;
}
.table1{
  border: black;
  border-width: 1px;
  width: 2006px;
  font-size: 20px;
  text-align: center;
}

.table2{
  margin-left: 1px;
  width: 970px;
  /* height: 100%; */
}
.Header{
  /* display: flex;
  flex-direction: row; */
  /* height: 58px; */
  width: 100%;
  overflow: auto;
}
.STATE{
  /* float: left; */
  font-size: 20px;
  /* border:1px solid black; */
   border-bottom:1px solid black;
  text-align: center;
  width: 292px;
  /* height: 29px; */
  /* margin-left: 2px; */
}
.ACTION{
 /* float: left; */
 font-size: 20px;
 /* display: flex;
 flex-direction: row; */
 /* margin: 1px; */
 width: 480px;
 border:1px solid black;
}
.actionChild{
  width: 480px;
  text-align: center;
  height: 35px;
  border-bottom:1px solid black;
}
.actionChildHeader{
  font-size: 20px;
  float: left;
  text-align: center;
  border-left:1px solid black;
  border-bottom: 1px solid black;
  height: 35px;
  flex: 1;
}
.actionChildHeaderFather{
  width: 480px;
  display: flex;
  flex-direction: row;
}
.GOTO{
  font-size: 20px;
  
  /* margin: 1px; */
  border:0;
}
.gotoChild{
  /* float: left; */
  border-bottom:1px solid black;
  text-align: center;
  height: 35px;
  width: 190px;
  text-align: center;
  font-size: 20px;
}
.gotoChildHeaderFather{
  width: 190px;
  display:flex;
  height: 35px;
  flex-direction: row;
  border-bottom:1px solid black;
}
.gotoChildHeader{
  float: left;
  font-size: 20px;
  margin-left: -1px;
  height: 35px;
  border-left:1px solid black;
  flex: 1;
}
</style>