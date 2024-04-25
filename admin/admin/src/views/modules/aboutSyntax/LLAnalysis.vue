<template>
  <div class="root">
    <div class="top">
      <div  class="top0">
          <table border="1">
            <tr>
              <div class="header1" style="width:610x; height:50px;">FIRST集</div>
            </tr>
            <tr >
              <div class="headerTotal1">字母</div>
              <div class="headerTotal2">集</div>
            </tr>
            <tr v-for="(item,index) in LLContentFrom.first" :key="index">
              <div class="headerTotal1">{{item[0]}}</div>
              <div class="headerTotal2">{{item[1]}}</div>
            </tr>
          </table>
      </div>
      <div class="top1" ref="lexicalForm">
        <textarea class="content" placeholder="请输入文法" v-model="LLContent.content" cols="30" rows="10">
        </textarea>
        <div class="but1">
          <el-row>
            <el-button type="primary" round @click="submit()" >一键分析</el-button>
          </el-row>
        </div>
      </div>
      <div class="top2">
           <table border="1" style="margin:0 auto;">
            <tr>
              <div class="header1" style="width:610x; height:50px;">FOLLOW集</div>
            </tr>
            <tr >
              <div class="headerTotal1" >字母</div>
              <div class="headerTotal2" >集</div>
            </tr>
            <tr v-for="(item,index) in LLContentFrom.follow" :key="index">
              <div class="headerTotal1">{{item[0]}}</div>
              <div class="headerTotal2">{{item[1]}}</div>
            </tr>
          </table>
      </div>
    </div> 
    <div class="middle" >
      <table border="1" align="center" style="margin:auto;">
        <div class="aaa">
          <p style="font-size:30px;"> select集</p>
        </div>
        <div>
          <td v-for="(item,index) in LLContentFrom.select" :key="index">
            <div class="middleContent">{{item[0]}}</div>
            <div class="middleContent">{{item[1]}}</div>
          </td>
        </div>
      </table>
    </div>
    <div class="bottom">
        <div class="bottom0 top0">
             <table border="1">
            <tr>
              <div class="header1">新FIRST集</div>
            </tr>
            <tr >
              <div class="headerTotal1" >字母</div>
              <div class="headerTotal2">集</div>
            </tr>
            <tr v-for="(it,index) in LLContentFrom.newFirst" :key="index">
              <div class="headerTotal1" >{{it[0]}}</div>
              <div class="headerTotal2" >{{it[1]}}</div>
              
              <!-- <td v-for="(item,index) in it" :key="index">{{item}}</td> -->
            </tr>
          </table>
        </div>
        <div class="bottom1 top1" ref="lexicalForm">
          <textarea class="content" placeholder="请输入表达式（文法之间以回车隔开）" v-model="LLExpression.expression" cols="30" rows="10">
          </textarea>
          <div class="bott1 but1">
            <el-row>
              <el-button type="primary" round @click="submit2()" >一键提交</el-button>
            </el-row>
          </div>
        </div>
        <div class="bottom2 top2">
             <table border="1">
            <tr>
              <div class="header1" style="width:610x; height:50px;">新FOLLOW集</div>
            </tr>
            <tr >
              <div class="headerTotal1" >字母</div>
              <div class="headerTotal2" >集</div>
            </tr>
            <tr v-for="(item,index) in LLContentFrom.newFollow" :key="index">
              <div class="headerTotal1">{{item[0]}}</div>
              <div class="headerTotal2">{{item[1]}}</div>
            </tr>
          </table>
        </div>
    </div>
    <div class="end" >
        <table border="1" align="center" style="margin:auto; text-align:center;">
        <div class="aaa">
          <p style="font-size:30px;"> 新select集</p>
        </div>
        <div>
          <td v-for="(item,index) in LLContentFrom.newSelect" :key="index">
            <div class="middleContent">{{item[0]}}</div>
            <div class="middleContent">{{item[1]}}</div>
          </td>
        </div>
      </table>
    </div>
    <div class="endBottom">
        <table border="1" align="center" style="margin:auto; text-align:center;">
            <div class="aaa">
          <p style="font-size:40px;height:60px">计算过程</p>
        </div>
        <div>
          <tr style="font-size:25px; text-align:center; height:40px;">
           <td style="width:400px;height:30px;">推导式</td>
           <td style="width:400px;height:30px;">匹配字符</td>
           <td style="width:400px;height:30px;">栈中剩余(剩下#成功)</td>
         </tr>
        </div>
         
         <div>
           <tr v-for="(content,index) in LLExpressionFrom.process" :key="index" >
            <td style="width:400px; font-size:25px" v-for="(con,ind) in content" :key="ind">{{con}}</td>
          </tr>
         </div>
          
        </table>
    </div>
  </div>
</template>

<script>
export default {
  name:'LLAnalysis',
  data() {
    return {
      LLContent:{
        content:''
      },
      LLExpression:{
        expression:''
      },
      LLContentFrom:{
        first:[['暂无数据']],
        follow:[['暂无数据']],
        select:[['']],
        judge:true,
        newFirst:[['暂无数据']],
        newFollow:[['暂无数据']],
        newSelect:[['']],
        // index:1
      },
      // data:[[['']]],
      LLExpressionFrom:{
        process:[['']]
      },
      point:0
    }
  },
  methods:{
    submit(){
        if(this.LLContent.content===""){
          this.$message('输入内容为空')
          return
        }
        this.$http({
            url:`LL/LLanalysis`,
            method: "post",
            data: this.LLContent
        }).then(({ data }) => {
          if(data.code==='200'){
              this.$message('数据输入错误，请重新输入')
               return
            }
          console.log(this.data)
            if (data!==null) {
                // this.$message({
                //     message: "操作成功",
                //     type: "success",
                //     duration: 1500,
                // });
                this.point++;
                for(let i=0;i<data.first.length;i++){
                  this.LLContentFrom.first[i]=data.first[i].split(' '),
                  this.LLContentFrom.follow[i]=data.follow[i].split(' ')
                }
                for(let i=0;i<data.newFirst.length;i++){
                  this.LLContentFrom.newFirst[i]=data.newFirst[i].split(' '),
                  this.LLContentFrom.newFollow[i]=data.newFollow[i].split(' ')
                }

                for(let i=0;i<data.select.length;i++){
                  this.LLContentFrom.select[i]=data.select[i].split(' ')
                }
                for(let i=0;i<data.newSelect.length;i++){
                   this.LLContentFrom.newSelect[i]=data.newSelect[i].split(' ')
                }
                console.log(this.LLContentFrom.newSelect)
                this.LLContentFrom.judge=data.judge
                 this.$forceUpdate();
                return
            } 
            this.$forceUpdate();
            this.$message('输入错误请重新输入')
        });
       
    },
    submit2(){
      if(this.LLExpression.expression===""){
           this.$message('输入内容为空')
          return
        }
        if(this.point===0){
           this.$message('不能进行LL(1)分析演示，需输入正确文法！！！')
           return
        }
        else
        this.$http({
            url:`LL/LLExpression`,
            method: "post",
            data: this.LLExpression
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
                
                for(let i=0;i<data.result.length;i++){
                    this.$set(this.LLExpressionFrom.process,i,data.result[i].split(' '))
                    // this.LLExpressionFrom.process[i]=data.result[i].split(' ')
                }
                console.log(this.LLExpressionFrom.process)
            } else {
                this.$message.error(data.msg);
            }
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
  text-align: center;
  background-color: #d9ecff;
  overflow: auto;
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
  overflow: auto;
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
   overflow: auto;
}
.top1{
  float: left;
  position: relative;
  margin-top: 0;
  height: 550px;
  margin-left: 5px;
  width: 750px;
  border-bottom-right-radius: 20px;
  border-bottom-left-radius: 20px;
  background-color:#d9ecff;
  top: 0;
}

.top2{
  float: left;
  position: relative;
  width: 620px;
  height: 550px;
  margin-top: 0px;
  margin-left: 5px;
  background-color:#d9ecff;
  padding-top: 0px;
  border-bottom-left-radius: 20px;
  top: 0;
  overflow: auto;
}
label{
  width: 90px;
}
textarea{
  resize: none;
}
.content{
  width: 710px;
  height: 440px;
  font-size: 30px;
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
  width: 200px;
  font-size: 20px;
  text-align: center;
  line-height: 60px;
  height: 60px;
  border:solid 1px black;
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
.header1{
  text-align: center;
  font-size: 20px;
  border: 1px solid black;
  width:610x; 
  height:50px;
}
.headerTotal1{
  float: left;
  text-align: center;
  font-size: 20px;
  border: 1px solid black;
  width: 100px;
}
.headerTotal2{
  float: left;
  text-align: center;
  font-size: 20px;
  border: 1px solid black;
  width: 510px;
}
.headerTotal{
  float: left;
  text-align: center;
  font-size: 20px;
  border: 1px solid black;
}
.middleHeader{
  text-align: center;
  font-size: 40px;
  width: 100%;
  height: 50px;
  display: flex;
  flex-direction: row;
  /* float: left; */
}
.middleContent{
  flex: 1;
  width: 150px;
  height: 40px;
  font-size: 20px;
  border: 1px solid black;
  text-align: center;
}
.endBottom{
  position: relative;
  text-align: center;
  margin-top: 6px;
  width: 2007px;
  background-color: #d9ecff;
  overflow: auto;
}
</style>