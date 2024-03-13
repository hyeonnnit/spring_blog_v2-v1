package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardPersistRepository {
    private final EntityManager em;

    public Board findById(Integer id){
        Board board = em.find(Board.class,id);
        return board;
    }
    public List<Board> findAll(){
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);
        return query.getResultList();
    }
    @Transactional
    public Board save(Board board){
        em.persist(board);
        return board;
    }
    @Transactional
    public void updateById(int id, BoardRequest.UpdateDTO reqDTO){
        Board board = findById(id);
        board.update(reqDTO);
    }
    @Transactional
    public void deleteById(Integer id){
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

//    @Transactional
//    public void deleteByIdv2(Integer id) {
//        Board board = findById(id);
//        em.remove(board); // PC에 객체를 지우고, (트랜잭션 종료 시) 삭제 쿼리를 전송함
//    }
}
