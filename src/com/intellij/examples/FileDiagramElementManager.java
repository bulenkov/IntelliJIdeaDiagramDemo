package com.intellij.examples;

import com.intellij.diagram.AbstractDiagramElementManager;
import com.intellij.diagram.presentation.DiagramState;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.SimpleColoredText;
import org.jetbrains.annotations.Nullable;

/**
 * @author Konstantin Bulenkov
 */
public class FileDiagramElementManager extends AbstractDiagramElementManager<VirtualFile> {
  @Nullable
  @Override
  public VirtualFile findInDataContext(DataContext context) {
    return CommonDataKeys.VIRTUAL_FILE.getData(context);
  }

  @Override
  public boolean isAcceptableAsNode(Object o) {
    return o instanceof VirtualFile;
  }

  @Nullable
  @Override
  public String getElementTitle(VirtualFile file) {
    return file.getName();
  }

  @Nullable
  @Override
  public SimpleColoredText getItemName(Object o, DiagramState state) {
    return null;
  }

  @Override
  public String getNodeTooltip(VirtualFile file) {
    return null;
  }
}
