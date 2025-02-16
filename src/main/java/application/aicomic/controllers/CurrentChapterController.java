package application.aicomic.controllers;

import application.aicomic.dataAccess.CurrentChapterDTO;
import application.aicomic.models.CurrentChapter;
import application.aicomic.services.CurrentChapterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/currentChapter")
@RestController
public class CurrentChapterController {
    private CurrentChapterService currentChapterService;

    @GetMapping("/getAll")
    public List<CurrentChapter> getAllCurrentChapter() {
        return currentChapterService.getAllCurrentChapter();
    }

    @PostMapping("/post")
    public CurrentChapter addCurrentChapter(@RequestBody CurrentChapter currentChapter) {
        return currentChapterService.addCurrentChapter(currentChapter);
    }

    @PutMapping("/update")
    public CurrentChapter updateCurrentChapter(@PathVariable String id, @RequestBody CurrentChapterDTO currentChapterDTO) {
        return currentChapterService.updateCurrentChapter(id, currentChapterDTO);
    }

    @GetMapping("/getById")
    public CurrentChapter getCurrentChapterById(@PathVariable String id) {
        return currentChapterService.getCurrentChapterById(id);
    }

    @DeleteMapping("/delete")
    public CurrentChapter deleteCurrentChapter(@PathVariable String id) {
        return currentChapterService.deleteCurrentChapter(id);
    }
}
