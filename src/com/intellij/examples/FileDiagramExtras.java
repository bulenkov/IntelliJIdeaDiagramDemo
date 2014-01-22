package com.intellij.examples;

import com.intellij.diagram.DiagramBuilder;
import com.intellij.diagram.DiagramNode;
import com.intellij.diagram.extras.DiagramExtras;
import com.intellij.diagram.extras.providers.DiagramDnDProvider;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Konstantin Bulenkov
 */
public class FileDiagramExtras extends DiagramExtras<VirtualFile> {
  private DiagramDnDProvider<VirtualFile> myDnDProvider = new DiagramDnDProvider<VirtualFile>() {
    @Override
    public boolean isAcceptedForDnD(Object o, Project project) {
      return o instanceof VirtualFile
          || o instanceof PsiElement;
    }


    @Nullable
    @Override
    public VirtualFile[] wrapToModelObject(Object o, Project project) {
      if (o instanceof PsiElement) {
        final PsiFile file = ((PsiElement) o).getContainingFile();
        if (file != null) {
          return new VirtualFile[]{file.getVirtualFile()};
        } else if (o instanceof PsiDirectory) {
          return new VirtualFile[]{((PsiDirectory) o).getVirtualFile()};
        }
      } else if (o instanceof VirtualFile) {
        return new VirtualFile[]{(VirtualFile) o};
      }
      return VirtualFile.EMPTY_ARRAY;
    }
  };

  @NotNull
  @Override
  public JComponent createNodeComponent(DiagramNode<VirtualFile> node, DiagramBuilder builder, Point basePoint) {
    if (node.getIdentifyingElement().getParent() == null) {
      return new JLabel(IconLoader.getIcon("/icons/hdd.png"));
    }
    return super.createNodeComponent(node, builder, basePoint);
  }

  @Nullable
  @Override
  public DiagramDnDProvider<VirtualFile> getDnDProvider() {
    return myDnDProvider;
  }

  @Nullable
  @Override
  public Object getData(String dataId, List<DiagramNode<VirtualFile>> nodes, DiagramBuilder builder) {
    if (nodes.size() == 1) {
      final VirtualFile file = nodes.get(0).getIdentifyingElement();
      if (CommonDataKeys.VIRTUAL_FILE.is(dataId)) {
        return file;
      }
      if (CommonDataKeys.PSI_FILE.is(dataId) || CommonDataKeys.PSI_ELEMENT.is(dataId)) {
        if (file.isDirectory() && CommonDataKeys.PSI_ELEMENT.is(dataId)) {
          return PsiManager.getInstance(builder.getProject()).findDirectory(file);
        } else {
          return PsiManager.getInstance(builder.getProject()).findFile(file);
        }
      }
    }
    return super.getData(dataId, nodes, builder);
  }
}
