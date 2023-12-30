# th!nk

---

>## th!nk 띵!
> 학기 초, 선생님들이 학생들의 이름과 자리를 더 쉽게 외울 수 있도록 하는 서비스입니다!

---

## 개발 환경
+ Java - OpenJDK 17.0.9
+ IDE : IntelliJ 2023.3
+ Framework : SpringBoot 3.2.0
+ ORM : JPA
+ Database Version : MariaDB 10.6.12

---

## 주요기능
>### <a href="https://github.com/Club-PARD/Meme-dong_Office_Server/blob/main/src/main/java/com/wepard/meme_dong_office/controller/students/list/StudentsListController.java">학생 리스트 CRUD</a>
> 유저가 학생의 정보를 입력하고 수정하고 삭제 할 수 있습니다.

>### <a href="https://github.com/Club-PARD/Meme-dong_Office_Server/blob/main/src/main/java/com/wepard/meme_dong_office/controller/students/StudentsController.java">개별 학생의 정보 CRUD</a>
> 학생의 정보 (사진, 알레르기 정보 등)을 입력하고 수정하고 삭제 할 수 있습니다.

>### <a href="https://github.com/Club-PARD/Meme-dong_Office_Server/blob/main/src/main/java/com/wepard/meme_dong_office/controller/auth/token/TokenController.java">로그인</a>
> 유저의 정보를 안전하게 보호하기 위해서 로그인 기능을 제공합니다.

---

## API
<details>
<summary>POST - /api/v1/auth/token</summary>
<div markdown="1">

  엑세스 토큰, 리프레시 토큰을 발급하는 API

  **406 에러 → 이메일 혹은 비밀번호 틀림**

  **Request**

    {
    	"email":"유저 이메일",
    	"password":"유저 비밀번호"
    }

  **Response**

    {
    	"accessToken":"엑세스 토큰 값",
    	"tokenType":"bearer",
    	"refreshToken":"리프레시 토큰 값",
    	"exprTime":3600, //3600초 후 엑세스 토큰이 만료됨
    	"userId":1 //유저의 아이디
    }
</div>
</details>



<details>
<summary>POST - /api/v1/auth/token/refresh</summary>
<div markdown="1">

  엑세스 토큰이 만료 되었을 때 리프레시 토큰으로 새로운 토큰들을 발급하는 API

  **Request**

    {
    	"refreshToken":"리프레시 토큰 값"
    }

  **Response**

    {
    	"accessToken":"새로운 엑세스 토큰 값",
    	"tokenType":"bearer",
    	"refreshToken":"새로운 리프레시 토큰 값",
    	"exprTime":"3600", //3600초 후 엑세스 토큰이 만료됨
    	"userId":1 //유저의 아이디
    }
</div>
</details>

<br>

<details>
<summary>POST - /api/v1/auth/users</summary>
<div markdown="1">

  유저 등록하는 api

  **409 에러 → 이메일 중복**

  **Request**

    {
    	"email":"유저 이메일",
    	"password":"유저 비밀번호",
    	"name":"유저 이름"
    }

  **Response**

    {
    	"accessToken":"엑세스 토큰 값",
    	"tokenType":"bearer",
    	"refreshToken":"리프레시 토큰 값",
    	"exprTime":3600, //3600초 후 엑세스 토큰이 만료됨
    	"userId":1 //유저의 아이디
    }
</div>
</details>


<details>
<summary>GET - /api/v1/users/{ id }</summary>
<div markdown="1">

  유저의 정보를 가져올 수 있는 API

  **Request**

    path parameter에 유저의 아이디 적기

  **Response**

    {
      "id": 1,
      "email": "유저 이메일",
      "name": "유저 이메일",
      "studentsList": [
        {
          "id": 1,
          "createdAt": "2023-12-28T14:33:41.218314",
          "name": "2011학년도 1학년 3반",
    			"listRow":"row 값",
    			"listCol":"col 값",
    			"seatSpacing":"몇 칸마다 띄울지",
          "studentsCount": 2,
          "studentsList": [
            {
              "id": 1,
              "name": "학생 1",
              "imageURL": "이미지 url",
              "birthday": "2004-08-06T05:32:38.8",
              "allergy": "알러지",
              "studyLevel": "공부 능력",
              "etc": "기타 내용",
              "caution": false //요주의 인물 
            },
            {
              "id": 2,
              "name": "학생 이름2",
              "imageURL": "이미지 url",
              "birthday": "2004-07-23T05:32:38.8",
              "allergy": "알러지",
              "studyLevel": "공부 능력",
              "etc": "기타 내용",
              "caution": false //요주의 인물
            }
          ]
        },
        {
          "id": 2,
          "createdAt": "2023-12-28T14:34:21.22761",
          "name": "2012학년도 2학년 5반",
    			"listRow":"row 값",
    			"listCol":"col 값",
    			"seatSpacing":"몇 칸마다 띄울지",
          "studentsCount": 2,
          "studentsList": [
            {
              "id": 3,
              "name": "학생 1",
              "imageURL": "이미지 url",
              "birthday": "2004-08-06T05:32:38.8",
              "allergy": "알러지",
              "studyLevel": "공부 능력",
              "etc": "기타 내용",
              "caution": false //요주의 인물 
            },
            {
              "id": 4,
              "name": "학생 이름2",
              "imageURL": "이미지 url",
              "birthday": "2004-07-23T05:32:38.8",
              "allergy": "알러지",
              "studyLevel": "공부 능력",
              "etc": "기타 내용",
              "caution": false //요주의 인물
            }
          ]
        }
      ]
    }
</div>
</details>

<details>
<summary>PATCH - /api/v1/users/{ id }</summary>
<div markdown="1">

  유저의 정보를 변경 할 수 있는 API

  **Request**

    path parameter에 유저의 아이디 적기
    
    {
    	"email":"유저 이메일",
    	"name":"유저 이름"
    	//추후 레벨 등 다른 요소 추가 가능
    }

  **Response**

    {
      "id": 1,
      "email": "유저 이메일",
      "name": "유저 이메일",
      "studentsList": [
        {
          "id": 1,
          "createdAt": "2023-12-28T14:33:41.218314",
          "name": "2011학년도 1학년 3반",
    			"listRow":"row 값",
    			"listCol":"col 값",
    			"seatSpacing":"몇 칸마다 띄울지",
          "studentsCount": 2,
          "studentsList": [
            {
              "id": 1,
              "name": "학생 1",
              "imageURL": "이미지 url",
              "birthday": "2004-08-06T05:32:38.8",
              "allergy": "알러지",
              "studyLevel": "공부 능력",
              "etc": "기타 내용",
              "caution": false //요주의 인물 
            },
            {
              "id": 2,
              "name": "학생 이름2",
              "imageURL": "이미지 url",
              "birthday": "2004-07-23T05:32:38.8",
              "allergy": "알러지",
              "studyLevel": "공부 능력",
              "etc": "기타 내용",
              "caution": false //요주의 인물
            }
          ]
        },
        {
          "id": 2,
          "createdAt": "2023-12-28T14:34:21.22761",
          "name": "2012학년도 2학년 5반",
    			"listRow":"row 값",
    			"listCol":"col 값",
    			"seatSpacing":"몇 칸마다 띄울지",
          "studentsCount": 2,
          "studentsList": [
            {
              "id": 3,
              "name": "학생 1",
              "imageURL": "이미지 url",
              "birthday": "2004-08-06T05:32:38.8",
              "allergy": "알러지",
              "studyLevel": "공부 능력",
              "etc": "기타 내용",
              "caution": false //요주의 인물 
            },
            {
              "id": 4,
              "name": "학생 이름2",
              "imageURL": "이미지 url",
              "birthday": "2004-07-23T05:32:38.8",
              "allergy": "알러지",
              "studyLevel": "공부 능력",
              "etc": "기타 내용",
              "caution": false //요주의 인물
            }
          ]
        }
      ]
    }
</div>
</details>

<details>
<summary>DELETE - /api/v1/users/{ id }</summary>
<div markdown="1">

  **Request**

    path parameter에 유저의 아이디 적기

  **Response**

    별도의 response 없이 상태코드 204만 보내줌
</div>
</details>

<br>


<details>
<summary>POST - /api/v1/students</summary>
<div markdown="1">

  개별 학생 정보를 등록하는 API

  **Request**

    {
      "studentsListId":1, //등록하고 싶은 학급(studentList)의 아이디 번호 써주기
      "name": "학생 명",
      "imageURL": "학생 사진 url",
      "birthday": "2023-12-28T05:52:45.378Z",
      "allergy": "알러지",
      "studyLevel": "학습 상태",
      "etc": "기타 정보",
      "caution": false //요주의 인물 여부
    }

  **Response**

    
    ****다른 정보 없이 status 201 띄워줌
    
    location 헤더에 어느 url로 가면 생성된 학생의 정보를 볼 수 있는지 적혀 있음
</div>
</details>

<details>
<summary>GET  - /api/v1/students/{ studentId }</summary>
<div markdown="1">

  개별 학생의 정보를 조회 할 수 있는 API

  **Request**

    path parameter에 학생 아이디 적기

  **Response**

    {
      "id":1, //등록 된 학생의 아이디
      "name": "학생 명",
      "imageURL": "학생 사진 url",
      "birthday": "2023-12-28T05:52:45.378Z",
      "allergy": "알러지",
      "studyLevel": "학습 상태",
      "etc": "기타 정보",
      "caution": false //요주의 인물 여부
    }
</div>
</details>

<details>
<summary>PATCH - /api/v1/students/{ studentId }</summary>
<div markdown="1">

  개별 학생의 정보를 변경 할 수 있는 API

  **모든 정보는 필수가 아님**

  **Request**

    {
      "studentsListId":1, //등록하고 싶은 학급(studentList)의 아이디 번호 써주기
      "name": "학생 명",
      "imageURL": "학생 사진 url",
      "birthday": "2023-12-28T05:52:45.378Z",
      "allergy": "알러지",
      "studyLevel": "학습 상태",
      "etc": "기타 정보",
      "caution": false //요주의 인물 여부
    }

  **Response**

    {
      "id":1, //등록 된 학생의 아이디
      "name": "학생 명",
      "imageURL": "학생 사진 url",
      "birthday": "2023-12-28T05:52:45.378Z",
      "allergy": "알러지",
      "studyLevel": "학습 상태",
      "etc": "기타 정보",
      "caution": false //요주의 인물 여부
    }
</div>
</details>

<details>
<summary>DELETE - /api/v1/students/{ studentId }</summary>
<div markdown="1">

  개별 학생을 삭제 할 수 있는 API

  **Request**

    path parameter에 학생 아이디 써주기

  **Response**

    별도의 response 없이 상태코드 204만 보내줌
</div>
</details>

<br>

<details>
<summary>POST - /api/v1/students/{ id }/images</summary>
<div markdown="1">

  학생의 사진 등록하는 API

  **Requtest**

    path parameter에 학생 아이디 써주기
    multipart로 이미지 보내주기

  **Response**

    다른 정보 없이 status 201 띄워줌
    
    location 헤더에 어느 url로 가면 이미지를 볼 수 있는지 적혀 있음
</div>
</details>

<details>
<summary>DELETE - /api/v1/students/{ id }/images</summary>
<div markdown="1">

  학생의 사진을 지우는 API

  **Request**

    path parameter에 학생 아이디 써주기

  **Response**

    별도의 response 없이 상태코드 204만 보내줌
</div>
</details>

<br>

<details>
<summary>POST - /api/v1/students/list</summary>
<div markdown="1">

  학생들의 정보를 한번에 입력할 수 있는 API

  ### **리스트에 담긴 순서 그대로 저장됨**

  **모든 정보를 필수로 적어야 하는건 아님**

  **Request**

    {
      "name": "저장 하고 싶은 이름", 
      "listRow":"row 값",
      "listCol":"col 값",
      "seatSpacing":"몇 칸마다 띄울지",
      "studentsList": [
        {
          "name": "학생 명",
          "imageURL": "학생 사진 url",
          "birthday": "2023-12-28T05:52:45.378Z",
          "allergy": "알러지",
          "studyLevel": "학습 상태",
          "etc": "기타 정보",
          "caution": false //요주의 인물 여부
        },
        {
          "name": "학생 명",
          "imageURL": "학생 사진 url",
          "birthday": "2023-12-28T05:52:45.378Z",
          "allergy": "알러지",
          "studyLevel": "학습 상태",
          "etc": "기타 정보",
          "caution": false //요주의 인물 여부
        }
      ]
    }

  **Response**

    다른 정보 없이 status 201 띄워줌
    
    location 헤더에 어느 url로 가면 생성된 학급의 정보를 볼 수 있는지 적혀 있음
</div>
</details>

<details>
<summary>GET - /api/v1/students/list/{ id }</summary>
<div markdown="1">

  학급(studentList)를 조회 할 수 있는 API

  **Request**

    path parameter에 학급 아이디 써주기

  **Response**

    {
      "name": "저장 하고 싶은 이름", 
      "listRow":"row 값",
      "listCol":"col 값",
      "seatSpacing":"몇 칸마다 띄울지",
      "studentsList": [
        {
          "name": "학생 명",
          "imageURL": "학생 사진 url",
          "birthday": "2023-12-28T05:52:45.378Z",
          "allergy": "알러지",
          "studyLevel": "학습 상태",
          "etc": "기타 정보",
          "caution": false //요주의 인물 여부
        },
        {
          "name": "학생 명",
          "imageURL": "학생 사진 url",
          "birthday": "2023-12-28T05:52:45.378Z",
          "allergy": "알러지",
          "studyLevel": "학습 상태",
          "etc": "기타 정보",
          "caution": false //요주의 인물 여부
        }
      ]
    }
</div>
</details>

<details>
<summary>GET - /api/v1/students/list/{id}/names</summary>
<div markdown="1">

  특정 학급의 이름만 가져오는 API

  **Request**

    path parameter에 학급 아이디 써주기

  **Response**

    [
    	"학생이름",
    	"학생이름",
    	"학생이름",
    	"학생이름",
    	"학생이름",
    	"학생이름"
    ]
</div>
</details>

<details>
<summary>PATCH - /api/v1/students/list/{ id }</summary>
<div markdown="1">

  학급(studentList)의 정보를 변경 할 수 있는 API

  **id를 제외한 모든 정보는 필수가 아님. 변경하고 싶은 정보만 써주면 됨**

  **학생들의 정보는 변경 할 수 없음!**

  **Request**

    {
      "name": "저장 하고 싶은 이름",
      "listRow":"row 값",
      "listCol":"col 값",
      "seatSpacing":"몇 칸마다 띄울지"
    }

  **Response**

    {
      "id":1, //학급 번호
      "name": "저장 하고 싶은 이름", 
      "listRow":"row 값",
      "listCol":"col 값",
      "seatSpacing":"몇 칸마다 띄울지",
      "studentsList": [
        {
          "name": "학생 명",
          "imageURL": "학생 사진 url",
          "birthday": "2023-12-28T05:52:45.378Z",
          "allergy": "알러지",
          "studyLevel": "학습 상태",
          "etc": "기타 정보",
          "caution": false //요주의 인물 여부
        },
        {
          "name": "학생 명",
          "imageURL": "학생 사진 url",
          "birthday": "2023-12-28T05:52:45.378Z",
          "allergy": "알러지",
          "studyLevel": "학습 상태",
          "etc": "기타 정보",
          "caution": false //요주의 인물 여부
        }
      ]
    }
</div>
</details>

<details>
<summary>DELETE - /api/v1/students/list/{ id }</summary>
<div markdown="1">

  특정 학급의 이름만 가져오는 API

  **Request**

      path parameter에 학급 아이디 써주기

  **Response**

      별도의 response 없이 상태코드 204만 보내줌
</div>
</details>

<br>