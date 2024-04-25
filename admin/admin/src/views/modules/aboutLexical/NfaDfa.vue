<template>
  <div class="root">

    <div class="top">
      <div class="top1">
        <div>
          <label></label>
          <div class="input1"><input  type="text" placeholder="请输入状态集" v-model="staticsForm.k"/></div>
        </div>
        <div>
          <label></label>
          <div  class="input2 input1" >
            <input type="text" placeholder="请输入字母" v-model="staticsForm.letters"/>
          </div>
        </div>
        <div class="exchange">
          <label></label>
          <div class="input3 input1">
            <input  type="text" placeholder="请输入状态转换函数（不同状态转换之间以空格隔开）" v-model="staticsForm.f"/>
          </div>
        </div>
        <div class="initialBox">
          <div  class="input4 input1" >
            <input type="text" placeholder="请输入初态集" v-model="staticsForm.s"/>
          </div>
        </div>
        <div class="endBox">
          <div class="input5 input1">
            <input type="text" placeholder="请输入终态集" v-model="staticsForm.z"/>
          </div>
        </div>
        <div class="but1">
          <el-row>
            <el-button type="primary" round @click="submit()" >一键分析</el-button>
          </el-row>
        </div>

      </div>
      <div class="topRight">
          <table class="table2" border="1" align="center" style="margin:auto;">
          <tr><th style="font-size:35px">NFA->DFA计算过程</th></tr>
          <div class="processBody">
              <div v-for="(item,index) in tArray" :key="index">
                  <div v-for="(content,i) in item" :key="i" class="queue">
                    {{content}}
                  </div>  
              </div>
          </div>
        </table>
      </div>
    </div>
    <div class="bottom">
      <table class="table1" border="1" style="font-size:20px ">
        <td>
          <tr><div style="width:140px; float:left">状态转换：</div><div class="box" v-for="(item,index) in result.change" :key="index">{{item}}</div></tr> 
          <tr><div style="width:140px; float:left">状态转换函数：</div><div class="box" v-for="(item,index) in result.func" :key="index" style="width:300px">{{item}}</div></tr> 
          <tr><div style="width:140px; float:left">终态集：</div><div class="box" v-for="(item,index) in result.endState" :key="index">{{item}}</div></tr>
          <tr><div style="width:140px; float:left">唯一的初态：</div><div class="box" v-if="result.Initial===-1">暂无数据</div><div v-else-if="result.Initial!==-1">{{result.Initial}}</div></tr>
          
        </td>
        
        
      </table>
      
    </div>
  </div>
</template>

<script>
export default {
  name:'NfaDfa',
  data() {
    return {
      staticsForm:{
        k:'',
        letters:'',
        f:'',
        s:'',
        z:'',
        n:0
      },
      result:{
        change:['暂无数据'],
        func:['暂无数据'],
        queue:[['暂无数据']],
        state:['暂无数据'],
        Initial:-1,
        endState:['暂无数据']
      },
      tArray: [["暂无数据"]]
    }
  },
  methods:{
    submit(){
      let judge=true
      this.staticsForm.n = this.staticsForm.k.length
      if(this.staticsForm.k===''||this.staticsForm.letters===''||this.staticsForm.f===''||this.staticsForm.s===''||this.staticsForm.z===''||this.staticsForm.n===''){
        this.$message('输入框中数据不可为空，请输入相应数据')
        return
      }
      
      this.$http({
            url:`lexical/NfaDfa`,
            method: "post",
            data: this.staticsForm
        }).then(({ data }) => {
          console.log(data.code)
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
                this.result.change=data.change,
                this.result.endState=data.z,
                this.result.func=data.f,
                this.result.Initial=data.s,
                this.result.state=data.k
                judge=false
            } 
            for(let i=0;i<data.index;i++){
              this.tArray[i]=data.queue[i].split(" ");
              for (const iterator of this.tArray[i]) {
                console.log(iterator)
              }
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
  text-align: center;
  background-color:#d9ecff;
  padding-top: 0px;
  border-bottom-left-radius: 20px;
  top: 0;
}
label{
  width: 90px;
}
.input1 {
  position: relative;
  margin-top: 1%;
  margin-left: 0;
  left: 12%;
  width: 680px;
  height: 65px;
}
.input2{
  position: relative;
  margin-left: 0;
  left: 12%;
  margin-top: 2%;
}
.input3{
  margin-left: 0;
  position: relative;
  left: 23%;
  height: 120px;
  margin-top: 3%;
}
.input4{
  margin-top: -2%;
}
.input5{
margin-top: 0.3%;
}
.div2{
  width: 200px;
  height: 40px;
}
input{
  width: 100%;
  height: 100%;
  font-size: 20px;
  border-radius: 5px;
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
.table1{
  border: black;
  border-width: 1px;
  width: 2006px;
  font-size: 20px;
  text-align: center;
}
.table2{
  border: black;
  border-width: 1px;
  width: 960px;
  text-align: center;
  font-size: 20px;
}
.box{
  width: 300px;
  float: left;
  border: 1px solid black;
  margin: 2px;
}
.queue{
  position: relative;
  border: black;
  border-width: 2px;
  border-style: solid;
  text-align: center;
  font-size: 23px;
  margin-top: 3px;
  margin-bottom: 3px;
  border-radius: 10px;
}
</style>