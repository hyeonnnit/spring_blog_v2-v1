package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {
    @Autowired
    private EntityManager em;
    @Autowired
    private BoardPersistRepository boardPersistRepository;
    @Test
    public void updateById_test() {
        // given
        int id = 1;
        String title = "제목수정1";

        // when
        Board board = boardPersistRepository.findById(id); //조회된 데이터! 영속화 됨!
        board.setTitle(title); //실제 쿼리가 날아간 건 아니고, pc에 있는 값을 변경한 것임
        //트랜젝션이 종료하면 업데이트가 날아가는데... 테스트에선 불가능하니까 em.flush 해줌
        em.flush();

    }
//    @Test
//    public void deleteByIdv2_test(){
//        // given
//        int id = 1;
//
//        // when
//        boardPersistRepository.deleteByIdv2(id);
//        em.flush();
//    }
    @Test
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        Board board = boardPersistRepository.findById(id);
        boardPersistRepository.deleteById(id);

        // then
        List<Board> boardList = boardPersistRepository.findAll();
        System.out.println("deleteById_test/size : " + boardList.size());
    }
    @Test
    public void findById_test() {
        // 1개의 id 두 번 전송 → 1개만 적용
//        //given
//        int id = 1;
//        //when
//        Board board = boardPersistRepository.findById(id);
//        System.out.println("findById_test : " + board);
//        //then
//        //org.assertj.core.api
//        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");
//        Assertions.assertThat(board.getContent()).isEqualTo("내용1");

        // 두 개의 id가 다른 경우 → 다 실행
        //given
        int id1 = 1;
        int id2 = 2;
        //when
        Board board1 = boardPersistRepository.findById(id1);
        Board board2 = boardPersistRepository.findById(id2);
        System.out.println("findById_test : " + board1);
        System.out.println("findById_test : " + board2);
    }
    @Test
    public void findAll_test(){
        //given - 지금은 넣을게 없음

        //when
        List<Board> boardList = boardPersistRepository.findAll();

        //then
        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername());

        //org.assertj.core.api
        Assertions.assertThat(boardList.size()).isEqualTo(4);
        Assertions.assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }
    @Test
    public void save_test() {
        // given
        Board board = new Board("제목", "내용5", "ssar");
        // when
        boardPersistRepository.save(board);

        // then
        System.out.println("save_test: " + board);
    }
}
