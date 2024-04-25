const base = {
    get() {
        return {
            url : "http://localhost:8080/zhinengxuexipingtai/",
            name: "zhinengxuexipingtai",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/zhinengxuexipingtai/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "编译原理教学演示系统"
        } 
    }
}
export default base
