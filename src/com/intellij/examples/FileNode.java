package com.intellij.examples;

import com.intellij.diagram.DiagramNodeBase;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Konstantin Bulenkov
 */
public class FileNode extends DiagramNodeBase<VirtualFile> {
  private final VirtualFile myFile;

  public FileNode(VirtualFile file) {
    super(FileDiagramProvider.getInstance());
    myFile = file;
  }

  @Nullable
  @Override
  public String getTooltip() {
    return getIdentifyingElement().getCanonicalPath();
  }

  @Override
  public Icon getIcon() {
    return myFile.isDirectory() ? AllIcons.Nodes.Folder : myFile.getFileType().getIcon();
  }

  @NotNull
  @Override
  public VirtualFile getIdentifyingElement() {
    return myFile;
  }
}
