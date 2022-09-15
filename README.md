# 2019_DrawingPanel
[JAVA] 2019년 2학년 1학기 Pattern-basedThinking&amp;Programming에서 자바를 이용해 만들었던 그림판 프로젝트

2019년 명지대학교 2학년 1학기 과목이었던 `패턴중심적사고와 프로그래밍` 강의에서 만들었던 `그림판 만들기` 프로젝트입니다. Java Swing Library를 통해 사각형, 타원, 다각형, 선, 삼각형 등 다양한 도형을 원하는 색과 스타일로 그려볼 수 있도록 제작했고, 프로젝트 과정을 통해  이벤트 처리에 대해 배울 수 있었습니다.

![image](https://user-images.githubusercontent.com/48820696/190319217-3b2aafae-f6ce-4c22-aca6-1a46d02e2be5.png)
https://youtu.be/M-MJEPqswoE
 

## 주요 기능 소개

---

| 번호 | 관련된 주요 클래스 | 기능 이름 | 기능 설명 | 비고 |
| --- | --- | --- | --- | --- |
| 1 | GFileMenu, GDrawingPanel | open, save, save as, new, closeopen시 다른파일을 열면 해당 파일을 저장하고 열것인지 아닌지를 선택하도록 함. 이 때 저장하는 창이 꺼져서 file이되는 경우를 방지해뒀다. |  |  |
| 2 | GFileMenu, GDrawingPanel | save 및 save as 구현시 xml 반복 예방 | 유저가 xml파일을 저장할 때 .xml을 붙여서 저장하는 경우가 있다. 그럴 경우 파일명이 file.xml.xml 이런식으로 저장된다. 이를 방지하기위해 .xml을 유저가 입력하였다면 중복되지 않고 file.xml로만 저장되도록 만들었다. |  |
| 3 | GFileMenu | print 구현 | 그린 도형을 print할 수 있다. print를 통해 pdf저장도 가능함. |  |
| 4 | GFileMenu , GEditMenu | menuitem사이에 seperator를 삽입 | menubar의 menu들에 menuitem사이에 seperator를 넣음 |  |
| 5 | GFileMenu , GEditMenu | menuitem에 tooltip으로 설명 | menubar의 menu들에 menuitem에 tooltip을 통해 설명을 넣었다.  툴팁은 2초정도 표시된 후 마우스가 머물더라도 사라지게 만들었다. |  |
| 6 | GFileMenu , GEditMenu | menuitem에 단축키를 부여 | menuitem의 단축키를 menuitem 맨 왼쪽에 표시하고 해당 키를 누르면 해당버튼의 이벤트를 발생하도록 했다. |  |
| 7 | GEditMenu, GDrawingPanel | 모두선택 기능, 삭제하기 기능 | Ctrl+A를 누르거나 편집메뉴의 "모두선택"을 누르면 도형이 전부 선택되면서 툴이 select tool로 변경된다. Ctrl+D를 누르거나 편집메뉴의 "삭제하기"를 누르면 선택된 도형이 사라진다. | 모두 선택되고 그리려고하면 셀렉된게 날아가기 때문에 유저의 편의를 고려하여 select으로 변경시킴. |
| 8 | GEditMenu, GDrawingPanel | 잘라내기 기능,복사하기 기능, 붙여넣기 기능 | Ctrl+x를 누르거나 편집메뉴의 "잘라내기"를 누르면 선택된 도형이 클립보드에 저장되고 드로윙패널에선 사라진다. Ctrl+c를 누르거나 편집메뉴의 "복사하기"를 누르면 선택된 도형이 클립보드에 저장된다.Ctrl+v를 누르거나 편집메뉴의 "붙여넣기"를 누르면 클립보드에 저장된 도형이 드로윙패널에  붙여진다.  붙여넣어도 순서가 바뀌지 않는다. |  |
| 9 | GEditMenu, GDrawingPanel | 붙여넣기 기능- 겹치지않게하기 | 붙여넣기 하면서 도형이 겹치지 않도록 만들었다. |  |
| 10 | GEditMenu, GDrawingPanel | 좌우반전 기능, 상하반전 기능 | 편집메뉴에서 "좌우뒤집기"를 누르면 선택된 도형들이 좌우가 반전되어 나타난다. 편집메뉴에서 "상하뒤집기"를 누르면 선택된 도형들이 상하가 반전되어 나타난다. |  |
| 11 | GEditMenu, GDrawingPanel | 맨 앞/뒤로 보내기 기능 | 편집메뉴에서 "맨 앞으로 보내기"혹은 "맨뒤로 보내기"를 누르면 선택된 도형들이 도형들 중 맨 앞으로 나타난다. | 복수선택하더라도 앞으로보내진 도형들의 순서가 뒤바뀌지않음. |
| 12 | GBottomPanel | 마우스의 좌표 표시 | 드로윙패널에 마우스가 있을 때 마우스의 좌표를 우측 하단에 표시하였다. |  |
| 13 | GBottomPanel | 현재 도구 표시 | 현재 어떤 transformer가 사용되고 있는지를 클래스 이름으로 우측 하단에 표시하였다. |  |
| 14 | GToolBar | select | 파랑색 테두리 안에 연한 파랑색으로 채워진 사각형이 부드럽게 그려지다가 마우스가 릴리즈되면 안에 있는 도형들이 전부 선택되어진다. 이를 통해 다중선택을 할 수 있으며 단독으로 도형을 클릭하더라도 도형이 선택된다. 또한 방향도 여러 방향으로 나아갈 수 있다. 또한 버튼위에 마우스가 위치하면 색이 반전된 아이콘이 나타나도록 만들었다. 툴팁이 나타나도록 만들었다. |  |
| 15 | GToolBar | 도형 아이콘 | 도형위에 계속하여 도형을 그릴 수있으며 버튼위에 마우스가 위치하면 색이 반전된 아이콘이 나타나도록 만들었다. 툴팁이 나타나도록 만들었다. |  |
| 16 | Gdrawingpanel | 회전하기 | 선택된 도형을 회전시킬 수 있다. |  |
| 17 | Gdrawingpanel | 움직이기 | 도형의 앵커를 이용해서 도형을 움직일 수 있다. |  |
| 18 | GDrawingpanel | 크기조절하기 | 도형을 어느 방향으로든 그릴 수 있다. |  |
| 19 | GToolBar | 삼각형 그리기 | 삼각형을 어느방향으로든 첫 기준점을 중심으로 그릴 수 있다. 또한 도형위에 계속하여 도형을 그릴 수있다. 도형이 선택되면 점선 테두리에 안의 원이 채워진 앵커들이 나타나도록 만들었다. |  |
| 20 | GToolBar | 직각 삼각형 그리기 | 직각 삼각형을 어느방향으로든 첫 기준점을 중심으로 그릴 수 있다. 또한 도형위에 계속하여 도형을 그릴 수있다. 도형이 선택되면 점선 테두리에 안의 원이 채워진 앵커들이 나타나도록 만들었다. |  |
| 21 | GToolBar | 오각형 그리기 | 오각형을 어느방향으로든 첫 기준점을 중심으로 그릴 수 있다. 또한 도형위에 계속하여 도형을 그릴 수있다. 도형이 선택되면 점선 테두리에 안의 원이 채워진 앵커들이 나타나도록 만들었다. |  |
| 22 | GToolBar | 육각형 그리기 | 육각형을 어느방향으로든 첫 기준점을 중심으로 그릴 수 있다. 또한 도형위에 계속하여 도형을 그릴 수있다. 도형이 선택되면 점선 테두리에 안의 원이 채워진 앵커들이 나타나도록 만들었다. |  |
| 23 | GToolBar | 다양한 선 색 고르기 , 다양한 채우기 색 고르기 | 툴바 아래에 겹쳐져있는 버튼들을 통해 JColorChoose로 기본제공하는 색보다 더 많은 색을 선택할 수 있다. (선택된 도형 및 그릴 도형에 적용도 됨) | 디자인을 고려하여 버튼을 겹쳐서선색을 더 뒤에 둠. & 디자인과 보기쉽게 하기위해 선색과 채우기색 버튼을 따로 구분해둠. |
| 24 | GToolBar | 선색과 채우기 색 서로 바꾸기 | 선택된 선색과 채우기색을 서로 바꿀 수 있다. (선택된 도형 및 그릴 도형에 적용도 됨) |  |
| 25 | GHomePanel | 선색버튼과 채우기색 버튼을 표시하고 어떤 부분이 선택되었는지를 표시한다. | . 선색과 채우기색을 투명도 없이  표시하고 선에 대한 색 선택인지 채우기에 대한 색인지 표시한다. toolbar와 연동되어 변경되고 도형 그리기 전에 설정한 값으로 도형이 그려진다. |  |
| 26 | GHomePanel | 선택된 색부분을 변경시킬  기본색상을 표시(null로 색을 아예안보이게 할 수도 있다.) | 유저가 원하는 특정 색이 없다면 기본으로 제공되는 10가지 색 (빨, 주, 노, 초, 파, 보, 흰, 회, 검, null)에서 고를 수 있도록 하였다. |  |
| 27 | GHomePanel | 선택된 도형의 선 두께를 조절하기 | 선택된 도형의 선 두께를 조절하며 도형 그리기 전에 값을 설정하면 이후 그려지는 도형에 반영이 된다. |  |
| 28 | GHomePanel | 선택된 도형의 선택된 부분(선, 채우기) 투명도를 조절하기 | 선택된 도형의 선택된 부분(채우기 색, 선색)의  투명도를 조절하며 도형 그리기 전에 값을 설정하면 이후 그려지는 도형에 반영이 된다. |  |
| 29 | GDrawingPanel, GHomePanel, GToolBar | homepanel과 toolbar에 있는 색 관련 버튼이 연동되어 변경됨. | toolbar에서 색을 변경한 것이 drawingpanel의 도형에도 영향을 주고 homepanel의 버튼에도 영향을 준다. 하지만 투명도는 유저가 홈패널을 통해확인할 수 있으므로 투명도를 적용하지 않은 색을 hompanel의 버튼들과 toolbar의 버튼들에 적용을 시켰다. 이미 유저가 그린 도형들을 선택하고 색을 변경하면 해당 도형이 그 색으로 변경되지만 셀렉된 상태에서 다른 도형을 누른다고 색의 값이 변하진 않는다. | 여러곳에서 도형눌린지 확인하고 클릭된 도형의 특성대로homepanel, toolbar에도 연동을 해야하는 점과 homepanel의 선택이 drawingpanel의 도형과 toolbar의 색버튼에도 영향을 주기 때문에 어렵다고 측정함. 연결하고 확인 하는 과정이 많이 힘들었다… |
| 30 | GDrawingPanel, GHomePanel, GToolBar | 선택된 도형, 선택된 색부분에 따라 투명도와 두께가 변경된다. | 어떤 도형이 클릭되었는지에 따라 선의 두께와 투명도가 홈패널의 적용되어 수치를 바꾸고 여기서 조절한 정도가 drawingpanel의 도형들 중 선택된 도형들에 영향을 미친다. | 그림판이나 포토샵보면 선택된 도형의 두께나 투명도가 함께 연동되어 변동되는 것을 보고 착안 & 여러곳에서 도형눌린지 확인하고 클릭된 도형의 특성대로homepanel, toolbar에도 연동을 해야하는 점과 homepanel의 선택이 drawingpanel의 도형과 toolbar의 색버튼에도 영향을 주기 때문에 어렵다고 측정함. |
| 31 | GPopUpRightClickMenu, GDrawingPanel | select 툴이면 우클릭 시 팝업메뉴가 나타나도록 함. | 도형을 그릴때에는 우클릭에서 나오는 메뉴들을 할 필요가 없어서 표시되지 않도록 하고 select일 때에 우클릭을 누르면 팝업메뉴가 나타나도록 함. |  |
| 32 | GDrawingPanel | paint 깜빡임 감소. | 도형을 그릴때 전체도형을 다시 하나하나 그리는 super();를 사용하지 않고 화면을 다 칠한 뒤 이미 있는 도형을 그리고 새로 그리는 도형만 깜빡임이 덜하도록 그렸다. | 아예 깜빡이지 않는 ppt나 한글처럼 하지는 못해서 3점 정도만 부여 |