/**
 * 数据转换为树形结构
 */
export function dataToTree (data, id = 'id', parentId = 'parentId', children = 'children') {
  var res = []
  for (let i = 0; i < data.length; i++) {
    let node = data[i]
    if (node[parentId]) {
      for (let j = 0; j < data.length; j++) {
        let treeNode = data[j]
        if (node[parentId] === treeNode[id]) {
          if (!treeNode[children]) {
            treeNode[children] = []
          }
          treeNode[children].push(node)
        }
      }
    } else {
      res.push(node)
    }
  }
  return res
}

/**
 * 树形结构转换为数据
 */
export function treeToData (data = [], res = []) {
  for (let i = 0; i < data.length; i++) {
    let node = data[i]
    res.push(node)
    if (node.children && node.children.length >= 1) {
      treeToData(node.children, res)
    }
  }
  return res
}
