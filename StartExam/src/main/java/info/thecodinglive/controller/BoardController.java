package info.thecodinglive.controller;

import java.security.Principal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import info.thecodinglive.model.Board;
import info.thecodinglive.model.Role;
import info.thecodinglive.model.User;
import info.thecodinglive.repository.BoardRepository;
import info.thecodinglive.repository.UserRepository;
import info.thecodinglive.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/home")
	public String Home() {
		return "board/home";
	}

	@RequestMapping("/form")
	public String Form() {
		return "board/form";
	}

	@PostMapping("/insert")
	@ResponseBody
	public ResponseEntity<Board> postBoard(Principal principal, @RequestBody Board board) {
		System.out.println("post request");
		System.out.println(board.toString());
		User user = userRepository.findByUsername(principal.getName());
		board.setUser(user);
		boardRepository.save(board);
		return new ResponseEntity<Board>(board, HttpStatus.CREATED);
	}

	@GetMapping("/list")
	public String getList(Model model,
			@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		boardService.findBoardList(pageable);
		model.addAttribute("boardList", boardService.findBoardList(pageable));

		return "board/list";
	}

	@RequestMapping("updat")
	public String updateviewboard(Model model, @AuthenticationPrincipal User user,
			@RequestParam(value = "id", defaultValue = "0") Long id) {
		Board board = boardService.findBoardById(id);
		model.addAttribute("board", board);
		return "board/form";
	}
	
	@RequestMapping("")
	public String board(Model model, @AuthenticationPrincipal User user,
			@RequestParam(value = "id", defaultValue = "0") Long id) {
		Board board = boardService.findBoardById(id);
		model.addAttribute("board", board);
		return "board/viewform";
	}

	@Transactional
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board reqBoard, 
			Principal principal) {

		Board board = boardService.findBoardById(id);
		User user = userRepository.findByUsername(principal.getName());
		if (board.getUser() == user || Role.ADMIN==user.getRole()) {
			board.setTitle(reqBoard.getTitle());
			board.setSubTitle(reqBoard.getSubTitle());
			board.setContent(reqBoard.getContent());

			System.out.println(board.toString());
			return new ResponseEntity<Board>(board, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBoard(@PathVariable Long id,Principal principal) {
		
		Board board = boardService.findBoardById(id);
		User user = userRepository.findByUsername(principal.getName());
		if (board.getUser() == user || Role.ADMIN==user.getRole()) {
			try {
				boardService.deleteById(id);
			} catch (Exception e) {
				System.out.println(e);
			}
			return new ResponseEntity<>("{}", HttpStatus.OK);

		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		
	}

}
