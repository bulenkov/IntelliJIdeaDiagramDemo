package com.intellij.examples;

import com.intellij.diagram.*;
import com.intellij.diagram.extras.DiagramExtras;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Konstantin Bulenkov
 */
public class FileDiagramProvider extends BaseDiagramProvider<VirtualFile> {

  public static final String ID = "FileDiagramProvider";
  private DiagramElementManager<VirtualFile> myElementManager = new FileDiagramElementManager();
  private DiagramVfsResolver<VirtualFile> myVfsResolver = new FileDiagramVfsResolver();
  private DiagramExtras<VirtualFile> myExtras = new FileDiagramExtras();
  private DiagramColorManager myColorManager = new FileDiagramColorManager();

  @Pattern("[a-zA-Z0-9_-]*")
  @Override
  public String getID() {
    return ID;
  }

  @Override
  public DiagramElementManager<VirtualFile> getElementManager() {
    return myElementManager;
  }

  @Override
  public DiagramVfsResolver<VirtualFile> getVfsResolver() {
    return myVfsResolver;
  }

  @Override
  public String getPresentableName() {
    return "File Diagram";
  }

  @NotNull
  @Override
  public DiagramExtras<VirtualFile> getExtras() {
    return myExtras;
  }

  @Override
  public FileDiagramDataModel createDataModel(@NotNull Project project, @Nullable VirtualFile file, @Nullable VirtualFile file2, DiagramPresentationModel model) {
    return new FileDiagramDataModel(project, file);
  }

  @Override
  public DiagramColorManager getColorManager() {
    return myColorManager;
  }

  public static FileDiagramProvider getInstance() {
    return (FileDiagramProvider)DiagramProvider.findByID(ID);
  }
}
