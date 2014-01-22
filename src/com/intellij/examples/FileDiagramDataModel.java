package com.intellij.examples;

import com.intellij.diagram.DiagramDataModel;
import com.intellij.diagram.DiagramNode;
import com.intellij.diagram.DiagramRelationshipInfo;
import com.intellij.diagram.DiagramRelationshipInfoAdapter;
import com.intellij.diagram.presentation.DiagramLineType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.ModificationTracker;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author Konstantin Bulenkov
 */
public class FileDiagramDataModel extends DiagramDataModel<VirtualFile> {
  private List<FileNode> myNodes = new ArrayList<FileNode>();
  private List<FileEdge> myEdges = new ArrayList<FileEdge>();
  private Map<String, FileNode> path2Node = new HashMap<String, FileNode>(myNodes.size());

  public FileDiagramDataModel(Project project, VirtualFile file) {
    super(project, FileDiagramProvider.getInstance());
    VirtualFile f = file;
    while (f != null) {
      final FileNode n = new FileNode(f);
      myNodes.add(n);
      path2Node.put(f.getPath(), n);
      f = f.getParent();
    }
    refreshDataModel();
  }

  @NotNull
  @Override
  public Collection<FileNode> getNodes() {
    return myNodes;
  }

  @NotNull
  @Override
  public Collection<FileEdge> getEdges() {
    return myEdges;
  }

  @NotNull
  @Override
  public String getNodeName(DiagramNode<VirtualFile> node) {
    return node.getIdentifyingElement().getName();
  }

  @Nullable
  @Override
  public FileNode addElement(VirtualFile file) {
    FileNode node = path2Node.get(file.getPath());
    if (node == null) {
      node = new FileNode(file);
      path2Node.put(file.getPath(), node);
      myNodes.add(node);
    }
    return node;
  }

  @Override
  public void refreshDataModel() {
    myEdges.clear();

    for (FileNode node : myNodes) {
      VirtualFile f = node.getIdentifyingElement().getParent();
      int i = 1;
      while (f != null) {
        final FileNode n = path2Node.get(f.getPath());
        if (n != null) {
          final int level = i;
          DiagramRelationshipInfo r = level == 1 ?
              FileDiagramRelationships.STRONG : new DiagramRelationshipInfoAdapter("SOFT", DiagramLineType.DASHED) {
            @Override
            public Shape getStartArrow() {
              return STANDARD;
            }

            @Override
            public String getLabel() {
              return "   " + String.valueOf(level);
            }
          };
          myEdges.add(new FileEdge(node, n, r));
          f = null;
        } else {
          f = f.getParent();
          i++;
        }
      }
    }
  }

  @Override
  public void removeNode(DiagramNode<VirtualFile> node) {
    myNodes.remove(node);
    path2Node.remove(node.getIdentifyingElement().getPath());
    refreshDataModel();
  }

  @NotNull
  @Override
  public ModificationTracker getModificationTracker() {
    return VirtualFileManager.getInstance();
  }

  @Override
  public void dispose() {

  }
}
