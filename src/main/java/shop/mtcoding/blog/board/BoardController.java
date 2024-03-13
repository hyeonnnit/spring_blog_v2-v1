package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardNativeRepository boardNativeRepository;
    private final BoardPersistRepository boardPersistRepository;

    @GetMapping( "/")
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardPersistRepository.findAll();
        request.setAttribute("boardList",boardList);
        return "index";
    }
    @PostMapping("/board/{id}/update")
    public String update (@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO){
//        System.out.println("id : " + id);
//        System.out.println("title : " + title);
//        System.out.println("content : " + content);
//        System.out.println("username : " + username);
        boardPersistRepository.updateById(id, reqDTO);
        return "redirect:/board/"+id;
    }
    @GetMapping("/board/{id}/update-form")
    public String updateForm (@PathVariable Integer id, HttpServletRequest request){
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board",board);
        return "board/update-form";
    }
    @PostMapping("/board/save")
    public String save( BoardRequest.SaveDTO reqDTO){
        boardPersistRepository.save(reqDTO.toEntity());
        return "redirect:/";
    }
    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id,HttpServletRequest request) {
        Board board = boardPersistRepository.findById(id);
        request.setAttribute("board", board);
        return "board/detail";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id){
        boardPersistRepository.deleteById(id);
        return "redirect:/";
    }
}
