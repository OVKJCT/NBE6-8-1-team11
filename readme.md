#목표
-좋은 소프트웨어를 만들자

[프로잭트 매니저]
프로잭트 매니저는 작업을 분배하는 담당

1. 리포 생성
2. readme 생성
3. setting의 Branches > Proejct matching branches > Require a pull request before merging
    1. 해당 기능은 메인에 바로 커밋 할 수 없고 PR을 통해서만 가능하다.
4. 개발자들 초대
5. 이슈 생성
    1. 할 일에 대한 할당, 라벨링, 기한 설정
6. PR 신청이 오면 리뷰
    
    ![image.png](attachment:2975f5ed-8eab-4d0b-a088-d21e61b87f03:image.png)
    
7. 리부 후 merge 시 Squash and merge로 커밋을 압축해서 병합
    
    ![image.png](attachment:eea23ab3-2643-4758-9153-d138a3a97644:image.png)
    
8. PR 머지 후 해당 브랜치 삭제, 이슈 닫기


[일반 개발자]
1. 첫 리포지토리 클론 OR 메인 pull
    1. git clone 주소 or (main) git pull origin main
2. 자신의 작업장인 브랜치 생성
    1. (main) git checkout -b enhancement/1
        1. 현재 자신은 main에 있고 브랜치를 enhancement/1로 이동할건데 없으면 만들어서 이동
3. 작업 후 커밋. 그리고 메인에 다른 사람이 반영한 내용을 내 브랜치에 반영 
    1.  git add .; git commit -m "작업내용”
        1. 변경사항 자신의 브랜치에 커밋
    2. (enhancement/1) git pull origin main --rebase
        1. **이 명령어는 작업 전 후로 습관적으로 해주어야 한다.**
        2. Git의 `pull` 명령어를 `rebase` 옵션과 함께 사용하는 것으로, 원격 저장소(`origin`)의 `main` 브랜치 변경 사항을 가져와 현재 로컬 브랜치에 **리베이스(rebase)** 방식으로 적용하라는 의미입니다.
4. 메인에 푸쉬
    1. (enhancement/1) git push origin enhancement/1
    2. (enhancement/1) git push origin enhancement/1 -f
- 5. PR 신청
    
    신청 시 description에 해당 이슈 번호를 적어야 한다 
    
    ![image.png](attachment:94ce82ca-4553-4530-a19a-9c61633db10c:image.png)
    
1. PR이 merge된 후 뒷정리
    1. git checkout main : 메인으로 이동
    2. git pull origin main : 최신화
    3. git branch -D enhancement/1 : 작업했던 브랜치 삭제
    4. git fetch —prune : 가지치기
