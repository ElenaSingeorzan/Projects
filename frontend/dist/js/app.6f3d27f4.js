(function(){"use strict";var t={2575:function(t,e,o){var n=o(5130),r=o(6768);const s={id:"app"},a={class:"container"};function i(t,e,o,n,i,l){const u=(0,r.g2)("router-link"),d=(0,r.g2)("router-view");return(0,r.uX)(),(0,r.CE)("div",s,[(0,r.Lk)("nav",null,[(0,r.bF)(u,{to:"/"},{default:(0,r.k6)((()=>e[0]||(e[0]=[(0,r.eW)("Home")]))),_:1}),(0,r.bF)(u,{to:"/create-post"},{default:(0,r.k6)((()=>e[1]||(e[1]=[(0,r.eW)("Create Post")]))),_:1}),(0,r.bF)(u,{to:"/login"},{default:(0,r.k6)((()=>e[2]||(e[2]=[(0,r.eW)(" Login")]))),_:1}),(0,r.bF)(u,{to:"/register"},{default:(0,r.k6)((()=>e[3]||(e[3]=[(0,r.eW)("Register")]))),_:1})]),(0,r.Lk)("div",a,[(0,r.bF)(d)])])}var l={name:"App"},u=o(1241);const d=(0,u.A)(l,[["render",i]]);var c=d,p=o(1387),m=o(4232);function f(t,e,o,n,s,a){return(0,r.uX)(),(0,r.CE)("div",null,[e[0]||(e[0]=(0,r.Lk)("h1",null,"Lista Postări",-1)),(0,r.Lk)("ul",null,[((0,r.uX)(!0),(0,r.CE)(r.FK,null,(0,r.pI)(s.posts,(t=>((0,r.uX)(),(0,r.CE)("li",{key:t.id},(0,m.v_)(t.title),1)))),128))])])}var h=o(4373);const v="http://localhost:8080/api",k=h.A.create({baseURL:"http://localhost:8080/api"}),b=t=>k.post("/users/register",t),L=()=>h.A.get(`${v}/posts`),g=t=>h.A.post(`${v}/posts`,t),C=t=>h.A.post(`${v}/users/login`,t);var w={data(){return{posts:[]}},methods:{fetchPosts(){L().then((t=>{this.posts=t.data})).catch((t=>{console.error("Eroare la preluarea postărilor:",t)}))}},mounted(){this.fetchPosts()}};const y=(0,u.A)(w,[["render",f],["__scopeId","data-v-926db57e"]]);var E=y;function A(t,e,o,n,s,a){const i=(0,r.g2)("comment-section");return(0,r.uX)(),(0,r.CE)("div",null,[(0,r.Lk)("h2",null,(0,m.v_)(s.post.title),1),(0,r.Lk)("p",null,(0,m.v_)(s.post.content),1),e[0]||(e[0]=(0,r.Lk)("h3",null,"Comments:",-1)),(0,r.bF)(i,{postId:s.post.id},null,8,["postId"])])}function P(t,e,o,s,a,i){return(0,r.uX)(),(0,r.CE)("div",null,[(0,r.Lk)("ul",null,[((0,r.uX)(!0),(0,r.CE)(r.FK,null,(0,r.pI)(a.comments,(t=>((0,r.uX)(),(0,r.CE)("li",{key:t.id},(0,m.v_)(t.content),1)))),128))]),(0,r.bo)((0,r.Lk)("textarea",{"onUpdate:modelValue":e[0]||(e[0]=t=>a.newCommentContent=t),placeholder:"Add a comment"},null,512),[[n.Jo,a.newCommentContent]]),(0,r.Lk)("button",{onClick:e[1]||(e[1]=(...t)=>i.addComment&&i.addComment(...t))},"Add Comment")])}var _={props:["postId"],data(){return{comments:[],newCommentContent:""}},methods:{fetchComments(){h.A.get(`/api/posts/${this.postId}/comments`).then((t=>{this.comments=t.data}))},addComment(){h.A.post("/api/comments",{post_id:this.postId,content:this.newCommentContent,user_id:1}).then((()=>{this.fetchComments(),this.newCommentContent=""}))}},mounted(){this.fetchComments()}};const U=(0,u.A)(_,[["render",P]]);var O=U,X={data(){return{post:{}}},components:{CommentSection:O},methods:{fetchPost(){const t=this.$route.params.id;h.A.get(`/api/posts/${t}`).then((t=>{this.post=t.data}))}},mounted(){this.fetchPost()}};const I=(0,u.A)(X,[["render",A]]);var $=I;function x(t,e,o,s,a,i){return(0,r.uX)(),(0,r.CE)("div",null,[e[6]||(e[6]=(0,r.Lk)("h1",null,"Creează o postare nouă",-1)),(0,r.Lk)("form",{onSubmit:e[2]||(e[2]=(0,n.D$)(((...t)=>i.submitPost&&i.submitPost(...t)),["prevent"]))},[(0,r.Lk)("div",null,[e[3]||(e[3]=(0,r.Lk)("label",{for:"title"},"Titlu:",-1)),(0,r.bo)((0,r.Lk)("input",{type:"text","onUpdate:modelValue":e[0]||(e[0]=t=>a.title=t),id:"title",required:""},null,512),[[n.Jo,a.title]])]),(0,r.Lk)("div",null,[e[4]||(e[4]=(0,r.Lk)("label",{for:"content"},"Conținut:",-1)),(0,r.bo)((0,r.Lk)("textarea",{"onUpdate:modelValue":e[1]||(e[1]=t=>a.content=t),id:"content",required:""},null,512),[[n.Jo,a.content]])]),e[5]||(e[5]=(0,r.Lk)("button",{type:"submit"},"Creează Postare",-1))],32)])}o(4114);var F={data(){return{title:"",content:"",userId:1}},methods:{submitPost(){const t={title:this.title,content:this.content,user:{id:this.userId},status:"PENDING"};g(t).then((t=>{console.log("Postare creată:",t.data),this.$router.push("/posts")})).catch((t=>{console.error("Eroare la crearea postării:",t)}))}}};const S=(0,u.A)(F,[["render",x]]);var j=S;const J={key:0};function V(t,e,o,s,a,i){return(0,r.uX)(),(0,r.CE)("div",null,[e[8]||(e[8]=(0,r.Lk)("h2",null,"Register",-1)),(0,r.Lk)("form",{onSubmit:e[3]||(e[3]=(0,n.D$)(((...t)=>i.createUser&&i.createUser(...t)),["prevent"]))},[(0,r.Lk)("div",null,[e[4]||(e[4]=(0,r.Lk)("label",{for:"name"},"Name",-1)),(0,r.bo)((0,r.Lk)("input",{type:"text","onUpdate:modelValue":e[0]||(e[0]=t=>a.name=t),required:""},null,512),[[n.Jo,a.name]])]),(0,r.Lk)("div",null,[e[5]||(e[5]=(0,r.Lk)("label",{for:"email"},"Email",-1)),(0,r.bo)((0,r.Lk)("input",{type:"email","onUpdate:modelValue":e[1]||(e[1]=t=>a.email=t),required:""},null,512),[[n.Jo,a.email]])]),(0,r.Lk)("div",null,[e[6]||(e[6]=(0,r.Lk)("label",{for:"password"},"Password",-1)),(0,r.bo)((0,r.Lk)("input",{type:"password","onUpdate:modelValue":e[2]||(e[2]=t=>a.password=t),required:""},null,512),[[n.Jo,a.password]])]),e[7]||(e[7]=(0,r.Lk)("button",{type:"submit"},"Register",-1))],32),a.message?((0,r.uX)(),(0,r.CE)("p",J,(0,m.v_)(a.message),1)):(0,r.Q3)("",!0)])}var q={data(){return{name:"",email:"",password:"",message:""}},methods:{createUser(){const t={name:this.name,email:this.email,password:this.password};b(t).then((t=>{console.log("Utilizator creat:",t.data),this.$router.push("/users")})).catch((t=>{console.error("Eroare la crearea utilizatorului:",t)}))}}};const T=(0,u.A)(q,[["render",V]]);var z=T;const D={key:0};function R(t,e,o,s,a,i){return(0,r.uX)(),(0,r.CE)("div",null,[e[6]||(e[6]=(0,r.Lk)("h2",null,"Login",-1)),(0,r.Lk)("form",{onSubmit:e[2]||(e[2]=(0,n.D$)(((...t)=>i.loginUser&&i.loginUser(...t)),["prevent"]))},[(0,r.Lk)("div",null,[e[3]||(e[3]=(0,r.Lk)("label",{for:"email"},"Email",-1)),(0,r.bo)((0,r.Lk)("input",{type:"email","onUpdate:modelValue":e[0]||(e[0]=t=>a.email=t),required:""},null,512),[[n.Jo,a.email]])]),(0,r.Lk)("div",null,[e[4]||(e[4]=(0,r.Lk)("label",{for:"password"},"Password",-1)),(0,r.bo)((0,r.Lk)("input",{type:"password","onUpdate:modelValue":e[1]||(e[1]=t=>a.password=t),required:""},null,512),[[n.Jo,a.password]])]),e[5]||(e[5]=(0,r.Lk)("button",{type:"submit"},"Login",-1))],32),a.message?((0,r.uX)(),(0,r.CE)("p",D,(0,m.v_)(a.message),1)):(0,r.Q3)("",!0)])}var W={data(){return{email:"",password:"",message:""}},methods:{loginUser(){const t={email:this.email,password:this.password};C(t).then((t=>{console.log("Autentificare reusita",t.data)})).catch((t=>{console.error("Eroare la autentificare",t)}))}}};const M=(0,u.A)(W,[["render",R]]);var N=M;const K=[{path:"/",component:E},{path:"/posts/:id",component:$},{path:"/create-post",component:j},{path:"/register",component:z},{path:"/login",component:N}],Q=(0,p.aE)({history:(0,p.LA)(),routes:K});var G=Q;o(9313);const H=(0,n.Ef)(c);H.use(G),H.mount("#app")}},e={};function o(n){var r=e[n];if(void 0!==r)return r.exports;var s=e[n]={exports:{}};return t[n].call(s.exports,s,s.exports,o),s.exports}o.m=t,function(){var t=[];o.O=function(e,n,r,s){if(!n){var a=1/0;for(d=0;d<t.length;d++){n=t[d][0],r=t[d][1],s=t[d][2];for(var i=!0,l=0;l<n.length;l++)(!1&s||a>=s)&&Object.keys(o.O).every((function(t){return o.O[t](n[l])}))?n.splice(l--,1):(i=!1,s<a&&(a=s));if(i){t.splice(d--,1);var u=r();void 0!==u&&(e=u)}}return e}s=s||0;for(var d=t.length;d>0&&t[d-1][2]>s;d--)t[d]=t[d-1];t[d]=[n,r,s]}}(),function(){o.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return o.d(e,{a:e}),e}}(),function(){o.d=function(t,e){for(var n in e)o.o(e,n)&&!o.o(t,n)&&Object.defineProperty(t,n,{enumerable:!0,get:e[n]})}}(),function(){o.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(t){if("object"===typeof window)return window}}()}(),function(){o.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)}}(),function(){o.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})}}(),function(){var t={524:0};o.O.j=function(e){return 0===t[e]};var e=function(e,n){var r,s,a=n[0],i=n[1],l=n[2],u=0;if(a.some((function(e){return 0!==t[e]}))){for(r in i)o.o(i,r)&&(o.m[r]=i[r]);if(l)var d=l(o)}for(e&&e(n);u<a.length;u++)s=a[u],o.o(t,s)&&t[s]&&t[s][0](),t[s]=0;return o.O(d)},n=self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[];n.forEach(e.bind(null,0)),n.push=e.bind(null,n.push.bind(n))}();var n=o.O(void 0,[504],(function(){return o(2575)}));n=o.O(n)})();
//# sourceMappingURL=app.6f3d27f4.js.map