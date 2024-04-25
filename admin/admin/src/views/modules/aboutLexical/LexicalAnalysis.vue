<template>
  <div class="root">
    <div class="top">
      <div class="top1" ref="lexicalForm">
        <textarea class="content" placeholder="请输入C语言程序片断" v-model="lexicalForm.program" cols="30" rows="10">
        </textarea>
        <div class="but1">
          <el-row>
            <el-button type="primary" round @click="submit()" >一键分析</el-button>
          </el-row>
        </div>
      </div>
      <div class="topRight">
        <img src="../../../assets/img/DFA.png" style="width:100%;" alt="">
      </div>
    </div> 
    <div class="bottom">
      <table border="1">
          <tr style="font-size:25px">
            <th style="width:200px; height:60px">文中符号</th>
            <th style="width:300px; height:60px">类型</th>
            <th style="width:520px; height:60px">对应DFA(具体的DFA参照右上图)</th>
            <th style="width:480px; height:60px">错误</th>
            <th style="width:480px; height:60px">处理</th>
          </tr>
          <td>
            <div class="box" v-for="(item,index) in lexicalForm1.symbol" :key="index">{{item}}</div>
          </td>
           <td>
            <div class="box box2" v-for="(item,index) in lexicalForm1.type" :key="index">{{item}}</div>
          </td>
           <td>
            <div class="box box3" v-for="(item,index) in lexicalForm1.dfa" :key="index">
              
                <!-- <img class="dfaImg" v-if="item!==''" :src="require(`../../../assets/img/${item}.png`)" /> -->
                <!-- {{item}} -->
                <h3 v-if="item==='0'">关键字:0-->1-->2</h3>
                <h3 v-if="item==='1'">标识符:0-->1-->2</h3>
                <h3 v-if="item==='2'">数字：0-->3-->4 </h3>
                <h3 v-if="item==='3'">单符号：0-->(5~20)</h3>
                <h3 v-if="item==='4'">双符号：0-->其他</h3>
            </div>
          </td>
           <td>
            <div class="box box4" v-for="(item,index) in lexicalForm1.error" :key="index">{{item}}</div>
          </td>
           <td>
            <div class="box box4" v-for="(item,index) in lexicalForm1.handle" :key="index">{{item}}</div>
          </td>
      </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import VueAxios from 'vue-axios'
export default {
  name:'LexicalAnalysis',
  data() {
    return {
      lexicalForm:{
        program:''
      },
      tableData: [
        {
          name: '文中名称',
          type: '类型',
          dfa: '过程',
          error:'错误',
          handle:'处理'
        }],
      lexicalForm1:{
        symbol:[''],
        type:[''],
        dfa:[''],
        error:[''],
        handle:[''],
        // index:1
      }
    }
  },
  methods:{
     
    submit(){
        let judge=true
        if(this.lexicalForm.program===""){
          this.$message('提交内容为空，请重新输入')
          return
        }
          
        this.$http({
            url:`lexical/lexicalAnalysis`,
            method: "post",
            data: this.lexicalForm
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
                this.lexicalForm1.dfa=data.dfa,
                this.lexicalForm1.error=data.error,
                this.lexicalForm1.handle=data.handle,
                this.lexicalForm1.symbol=data.symbol,
                this.lexicalForm1.type=data.type
                judge=false;
                console.log(judge)
                return
            }
            if(judge){
              this.$message.error('数据输入错误，请重新输入');
            }
        });
        console.log(judge)
        
        
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
.bottom{
  position: relative;
  margin-top: 150px;
  width: 2007px;
  top: 0;
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
.topRight{
  position: absolute;
  width: 980px;
  height: 550px;
  margin-top: 0px;
  margin-left: 1028px;
  background-color:#d9ecff;
  padding-top: 0px;
  border-bottom-left-radius: 20px;
  overflow: auto;
  top: 0;
}
label{
  width: 90px;
}
textarea{
  resize: none;
}
.content{
  width: 960px;
  height: 440px;
  font-size: 25px;
  margin-top: 1%;
  margin-left: 1%;
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
  height: 100px;
  border:solid 1px black;
  margin-top: 0;
}
.box2{
  margin-top: 0;
width: 300px;
}
.box3{
  width: 520px;
}
.box4{
  margin-top: 0;
  width: 480px;
}
.dfaImg{
  width:500px;
  padding: 0;
}
</style>