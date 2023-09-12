# mysite 신동헌

## 최종 과제
```markdown

- no: 게시글 번호
- title: 게시글 제목
- contents: 게시글 내용
- hit: 조회수
- regDate: 등록일자
- groupNo: 게시글 그룹 번호
- orderNo: 게시글 순서
- depth: 게시글 깊이
- userNo: 게시글 작성자 번호
- userName: 게시글 작성자 이름

각 멤버 변수는 getter와 setter 메서드를 가지고 있습니다.   
이 클래스는 toString() 메서드를 오버라이딩하여 객체의 정보를 문자열로 반환합니다.   


```



---

### [controller](https://github.com/githubmendong/mysite/tree/main/mysite02/src/main/java/com/poscodx/mysite/controller)
 * [BoardController.java](https://github.com/githubmendong/mysite/blob/main/mysite02/src/main/java/com/poscodx/mysite/controller/BoardController.java)
 * [GuestbookController.java](https://github.com/githubmendong/mysite/blob/main/mysite02/src/main/java/com/poscodx/mysite/controller/GuestbookController.java)
 * [MainController.java](https://github.com/githubmendong/mysite/blob/main/mysite02/src/main/java/com/poscodx/mysite/controller/MainController.java)
 * [UserController.java](https://github.com/githubmendong/mysite/blob/main/mysite02/src/main/java/com/poscodx/mysite/controller/FUserController.java)

### [dao](dao)   
* [GuestBookDao.java](GuestBookDao.java)
* [UserDao.java](UserDao.java)

### [vo]( dao)
* [GuestBookVo.java](GuestBookVo.java)
* [UserVo.java](UserVo.java)

### [controller]()
* [AddAction.java]( AddAction.java)
* [CookieServlet.java]( CookieServlet.java)
* [DeleteAction.java](   DeleteAction.java)
* [DeleteFormAction.java](   DeleteFormAction.java)
* [GuestbookActionFactory.java](   GuestbookActionFactory.java)
* [ListAction.java](   ListAction.java)

### [main](  main)
* [MainAction.java](  main%2FMainAction.java)
* [MainActionFactory.java](  main%2FMainActionFactory.java)

### [user](  user)
* [JoinAction.java](   JoinAction.java)
* [JoinFormAction.java](   JoinFormAction.java)
* [JoinSuccessAction.java](   JoinSuccessAction.java)
* [LoginAction.java](   LoginAction.java)
* [LoginformAction.java](   LoginformAction.java)
* [LogoutAction.java](   LogoutAction.java)
* [UpdateAction.java](   UpdateAction.java)
* [UpdateformAction.java](   UpdateformAction.java)
* [UserActionFactory.java](  UserActionFactory.java)

### [utils]( utils)
* [WebUtil.java](WebUtil.java)

### [mvc](mvc)
* [Action.java](Action.java)
* [ActionFactory.java](ActionFactory.java)   

### [EncodingFilter.java](EncodingFilter.java)


---

### [views]( views)   
* [board](  board)
* [list.jsp](   list.jsp)
* [modify.jsp](   modify.jsp)
* [view.jsp](   view.jsp)
* [write.jsp](   write.jsp)

### [guestbook](  guestbook)
* [deleteform.jsp](  deleteform.jsp)
* [index.jsp](  index.jsp)
* [list.jsp](  list.jsp)

### [includes](  includes)
* [footer.jsp]( footer.jsp)
* [header.jsp](  header.jsp)
* [navigation.jsp](navigation.jsp)

### [main](  main)
* [index.jsp](index.jsp)

### [user](  user)
* [joinform.jsp](  joinform.jsp)
* [joinsuccess.html](  joinsuccess.html)
* [joinsuccess.jsp](  joinsuccess.jsp)
* [loginform.jsp](  loginform.jsp)
* [updateform.jsp](  updateform.jsp)


### [web.xml]( web.xml)
### [pom.xml](pom.xml)