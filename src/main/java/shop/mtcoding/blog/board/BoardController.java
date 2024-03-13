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

    @GetMapping( "/")
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardNativeRepository.findAll();
        request.setAttribute("boardList",boardList);
        return "index";
    }
    @PostMapping("/board/{id}/update")
    public String update (@PathVariable Integer id, String title, String content, String username){
//        System.out.println("id : " + id);
//        System.out.println("title : " + title);
//        System.out.println("content : " + content);
//        System.out.println("username : " + username);
        boardNativeRepository.updateById(id, title,content,username);
        return "redirect:/board/"+id;
    }
    @GetMapping("/board/{id}/update-form")
    public String updateForm (@PathVariable Integer id, HttpServletRequest request){
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board",board);
        return "board/update-form";
    }
    @PostMapping("/board/save")
    public String save(String username, String title, String content){
//        System.out.println("username : " + username);
//        System.out.println("username : " + title);
//        System.out.println("username : " + content);
        boardNativeRepository.save(title, content, username);
        return "redirect:/";
    }
    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id,HttpServletRequest request) {
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);
        return "board/detail";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id){
        boardNativeRepository.deleteById(id);
        return "redirect:/";
    }
}
