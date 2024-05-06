<script>
import {borrowBook, returnBook, searchBook,} from "@/api/book.js";
import {onMounted, ref} from "vue";
import {getUId, getUserName} from "@/until/auth.js";
import {ElMessage} from "element-plus";

export default {
  name: "table",
  setup() {
    const borrowerName = ref('');
    const bookName = ref('');
    const tableData = ref([]);
    const pageSize = ref(5);
    const pageNum = ref(1);
    const total = ref(0);
    const pages = ref(0);
    const postData = ref({
      borrowerName: "",
      bookName: "",
      pageNume: pageNum.value,
      pageSize: pageSize.value,
    })
    onMounted(async () => {
      const {data} = await searchBook(postData.value);
      console.log(data)
      tableData.value = data.list;
      total.value = data.total;
      pages.value = data.pages;
    })

    const search = async () => {
      // 根据借阅人和图书名称进行搜索，更新tableData数据
      postData.value.bookName = bookName.value;
      postData.value.borrowerName = borrowerName.value;
      console.log(postData.value);
      const {data} = await searchBook(postData.value);
      tableData.value = data.list;
      total.value = data.total;
      pages.value = data.pages;
    };

    const borrow = async (index, row) => {
      if (row.borrowStatus === "1") {
        ElMessage.error("不可重复借阅")
        return;
      }
      const borrowDto = {
        borrowerName: getUserName(),
        borrowerId: getUId(),
        id: row.id,
      }
      await borrowBook(borrowDto);
      setTimeout(() => {
        window.location.reload();
      }, 1000);
    };

    const returnBookMethod = async (index, row) => {
      if (row.borrowStatus === "0") {
        ElMessage.error("不可重复还书")
        return;
      }
      if (row.borrowerName !== getUserName()) {
        ElMessage.error("非法操作还书");
        return;
      }
      const returnData = {
        id: row.id,
        borrowerName: row.borrowerName,
        borrowerId: getUId()
      }
      await returnBook(returnData);
      setTimeout(() => {
        window.location.reload();
      }, 1000);
    };

    const reset = () => {
      borrower.value = '';
      bookName.value = '';
      // 清空tableData数据
    };
    const handleSizeChange = (number) => {
      pageSize.value = number;
      postData.value.pageSize = number;
      search()
    }
    const handleCurrentChange = (number) => {
      pageNum.value = number;
      postData.value.pageNum = number;
      search()
    }

    return {
      borrowerName,
      bookName,
      tableData,
      search,
      pageSize,
      total,
      pages,
      pageNum,
      reset,
      borrow,
      returnBook: returnBookMethod,
      handleSizeChange,
      handleCurrentChange
    };
  }
}

</script>

<template>
  <div class="borrow-container">
    <el-input v-model="borrowerName" placeholder="借阅人" class="input-style"></el-input>
    <el-input v-model="bookName" placeholder="图书名称" class="input-style"></el-input>
    <el-button type="primary" @click="search" class="button-style">查询</el-button>
    <el-button @click="reset" class="button-style">重置</el-button>
    <el-table :data="tableData" style="width: 100%" class="table-style">
      <el-table-column prop="bookName" label="图书名称" width="180"></el-table-column>
      <el-table-column prop="borrowTime" label="借阅时间" width="180"></el-table-column>
      <el-table-column prop="returnTime" label="归还时间" width="180"></el-table-column>
      <el-table-column prop="borrowStatusName" label="借阅状态" width="180"></el-table-column>
      <el-table-column prop="borrowerName" label="借阅人" width="180"></el-table-column>
      <el-table-column label="操作" width="200">
        <template #default="scope">
          <el-button size="small" type="text" @click="borrow(scope.$index, scope.row)">借书</el-button>
          <el-button size="small" type="text" @click="returnBook(scope.$index, scope.row)">还书</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        v-model:current-page="pageNum"
        v-model:page-size="pageSize"
        :page-sizes="[5, 10, 15,20]"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>
</template>

<style scoped>
.borrow-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
  box-sizing: border-box;
}

.input-style {
  margin-bottom: 10px;
  width: 200px;
}

.button-style {
  margin-right: 10px;
}

.table-style {
  margin-top: 20px;
}

.table-style .el-table__body tr {
  background-color: #fff;
}

.table-style .el-table__body tr:nth-child(odd) {
  background-color: #f9f9f9;
}

.table-style .el-button {
  margin-right: 5px;
}
</style>